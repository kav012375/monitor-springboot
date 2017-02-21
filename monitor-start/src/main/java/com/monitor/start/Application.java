package com.monitor.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zeusw on 2017/2/15.
 */
@SpringBootApplication
@ComponentScan(value = {
        "com.monitor.web",
        "com.monitor.service",
        "com.monitor.dal",
        "com.monitor.start",
        "com.monitor.security"})
public class Application {
    public static void main(String[] args) throws Exception {
        List<String> argsList = new ArrayList<String>(Arrays.asList(args));
        argsList.add("-Xms1024m");
        argsList.add("-Xmx2048m");
        //argsList.add("--debug");
        args = argsList.toArray(new String[argsList.size()]);
        //args = new String[]{"-Xms1024m","-Xmx2048m"};
        SpringApplication.run(Application.class,args);
    }
}
