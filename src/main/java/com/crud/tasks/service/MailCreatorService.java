package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://lukisek88.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("goodbye", "See you next time");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_mail", companyConfig.getCompanyMail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
    //
    public String countTasksMail(String message) {
        long count = taskRepository.count();
        String taskMessage = count == 1 ? "Currently in database you got: " + count + " task"
                : "Currently in database you got: " + count + " tasks";
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://lukisek88.github.io");
        context.setVariable("goodbye", "Bye ByE");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("taskQuantity", taskMessage);
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_mail", companyConfig.getCompanyMail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        return templateEngine.process("mail/number-of-tasks-per-day", context);
    }
}