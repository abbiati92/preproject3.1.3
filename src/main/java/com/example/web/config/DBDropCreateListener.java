package com.example.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBDropCreateListener implements ApplicationListener<ContextClosedEvent> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        jdbcTemplate.execute("DROP DATABASE abbiati92");
        jdbcTemplate.execute("CREATE DATABASE abbiati92");
    }
}
