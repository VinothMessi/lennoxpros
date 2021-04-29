package com.liidaveqa.lennoxpros.workflow;

import com.liidaveqa.lennoxpros.pages.*;
import com.liidaveqa.lennoxpros.services.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Lazy
@Component
@Logger
public class CreatingALead {

    @Autowired
    private HomePage homePage;

    @Autowired
    private SignInPage signInPage;

    @Autowired
    private WelcomePage welcomePage;

    @Autowired
    private LeadsPage leadsPage;

    @Autowired
    private AddLeadsPage addLeadsPage;

    public CreatingALead homePage(Consumer<HomePage> c) {
        c.accept(this.homePage);
        return this;
    }

    public CreatingALead signInPage(Consumer<SignInPage> c) {
        c.accept(this.signInPage);
        return this;
    }

    public CreatingALead welcomePage(Consumer<WelcomePage> c) {
        c.accept(this.welcomePage);
        return this;
    }

    public CreatingALead leadsPage(Consumer<LeadsPage> c) {
        c.accept(this.leadsPage);
        return this;
    }

    public CreatingALead addLeadsPage(Consumer<AddLeadsPage> c) {
        c.accept(this.addLeadsPage);
        return this;
    }
}