package com.example.petshelter.bot.timer;

import com.example.petshelter.bot.listener.TelegramBotUpdatesListener;
import com.example.petshelter.entity.*;
import com.example.petshelter.entity.shelter.CatShelter;
import com.example.petshelter.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class MessageToVolunteerTimer {

    private final MessageToVolunteerService messageToVolunteerService;
    private final UserService userService;
    private final CatOwnerService catOwnerService;
    private final TrialPeriodService trialPeriodService;
    private final TelegramBotUpdatesListener telegramBotUpdatesListener;
    private final CatShelterService catShelterService;
    private final CatService catService;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void checkMessage() {
        Boolean check = messageToVolunteerService.checker();
        if (check) {
            List<String> messages = messageToVolunteerService.findAllUnread();
            List<Long> id = userService.returnVolunteerTgId();
            for (Long aLong : id) {
                for (String message : messages) {
                    if (aLong != null) {
                        telegramBotUpdatesListener.sendM(aLong, message);
                    }
                }
            }
        }
    }

    @Scheduled(cron = "0 0 18 * * ?")
    public void checkReport() {
        List<CatOwner> catOwners = catOwnerService.findAll();
        for (CatOwner catOwner : catOwners) {
            if (!catOwner.getTrialPeriodsInActiveStatus().isEmpty()) {
                for (TrialPeriod trialPeriodsInActiveStatus : catOwner.getTrialPeriodsInActiveStatus()) {
                    if (trialPeriodsInActiveStatus.getReports().size() !=
                            trialPeriodsInActiveStatus.getEndDate().getDayOfYear() - trialPeriodsInActiveStatus.getStartDate().getDayOfYear()) {
                        telegramBotUpdatesListener.sendM(userService.findByPhone(catOwner.getPhoneNumber()).getTelegramId(), "Вы забыли отправить отчет! Отправьте его до 21:00");
                    } else {
                        telegramBotUpdatesListener.sendM(userService.findByPhone(catOwner.getPhoneNumber()).getTelegramId(), "Ваш испытательный срок закончен, обратитесь к волонтеру");
                        List<Long> id = userService.returnVolunteerTgId();
                        for (Long aLong : id) {
                            if (aLong != null) {
                                telegramBotUpdatesListener.sendM(aLong, catOwner.getPhoneNumber() + " закончил испытательный срок. Нужно принять решение!");
                            }
                        }
                        trialPeriodsInActiveStatus.setResult(TrialPeriodResult.SUCCESS);
                        trialPeriodService.createTrialPeriod(trialPeriodsInActiveStatus);
                        Cat cat = catService.findByName(trialPeriodsInActiveStatus.getAnimalName());
                        cat.setStatusAnimal(StatusAnimal.HAS_HOUSE);
                        catService.createCat(cat);
                        CatShelter catShelter = catShelterService.findById(1L);
                        catShelter.getCats().remove(cat);
                        catShelterService.createCatShelter(catShelter);
                        catOwner.getTrialPeriodsCompleted().add(trialPeriodsInActiveStatus);
                        catOwner.getTrialPeriodsInActiveStatus().remove(trialPeriodsInActiveStatus);
                        catOwnerService.createCatOwner(catOwner);
                    }
                }
            }
        }
    }

    @Scheduled(cron = "0 0 21 * * ?")
    public void checkReportAndSendMessageToVolunteer() {
        List<CatOwner> catOwners = catOwnerService.findAll();
        for (CatOwner catOwner : catOwners) {
            if (!catOwner.getTrialPeriodsInActiveStatus().isEmpty()) {
                for (TrialPeriod trialPeriodsInActiveStatus : catOwner.getTrialPeriodsInActiveStatus()) {
                    if (trialPeriodsInActiveStatus.getReports().size() !=
                            trialPeriodsInActiveStatus.getEndDate().getDayOfYear() - trialPeriodsInActiveStatus.getStartDate().getDayOfYear()) {
                        List<Long> id = userService.returnVolunteerTgId();
                        for (Long aLong : id) {
                            if (aLong != null) {
                                telegramBotUpdatesListener.sendM(aLong, catOwner.getPhoneNumber() + " не отправил отчет сегодня!");
                            }
                        }
                    }
                }
            }
        }
    }
}
