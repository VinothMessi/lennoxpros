package com.liidaveqa.lennoxpros.services;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Logger
@Scope("prototype")
public class TakeASnap {

	@Autowired
	private ApplicationContext appCtx;

	@Value("${user.dir}" + "${snap.path}")
	private String snapPath;

	public String saveAs(final String imageName) {
		String path = "";
		try {
			FileUtils.copyFile(printScreen(), create(imageName));
			path = create(imageName).getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	private File printScreen() {
		return appCtx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE);
	}

	private File create(final String imageName) {
		return new File(this.snapPath + imageName);
	}
}