
# 📊 Cucumber Branch

This branch is dedicated to the integration of **Cucumber BDD (Behavior-Driven Development)** into the RoundCube WebMail automation framework. The main goal of this branch is to explore and implement Cucumber-style scenarios while preserving the robustness and structure of the original TestNG-based framework.

Key differences from the `main` branch:

- ✅ Introduces **Cucumber with Gherkin syntax** for scenario definitions
- ✅ Adds **Cucumber Hooks** to manage setup/teardown per scenario
- ✅ Implements **automatic screenshot capture on scenario failure** via `@After` hook
- ✅ Retains the use of decorators like `ScreenshotOnFailureDecorator` and `PerformanceTimerDecorator`
- ✅ Designed to remain compatible with existing TestNG utilities
- 🚫 **TestNG listeners for screenshot capture are not used** (replaced by Cucumber lifecycle hooks)

> This branch will **not be merged into main**. Use it to evaluate Cucumber integration or run behavior-driven tests with step definitions mapped to feature files.
---

# 📬 RoundCube WebMail Automation Testing Project

This project is a robust and flexible end-to-end test automation suite for the RoundCube webmail system. It's built using **Java**, **Selenium WebDriver**, **TestNG**, and **Cucumber**, and follows a clean **Page Object Model (POM)** architecture. It supports multiple browsers, environments, reusable components, and integrates with **ReportPortal** for real-time test reporting.

---

## ✅ Test Automation Architecture & Features

- ✔ Linear, readable test scenarios with stable assertions
- ✔ 3 core test flows implemented:
  - **Delete Email**
  - **Mark Email as Spam**
  - **Send Draft Mail**
- ✔ Hybrid framework: Cucumber + TestNG decorators and utilities
- ✔ Cucumber-style feature files and step definitions
- ✔ Custom waits and usage of WebDriver API (`click()`, `getText()`, `switchTo()`, etc.)
- ✔ Mix of locator strategies: `id`, `cssSelector`, `tagName`, etc.
- ✔ Full support for **explicit waits** (no implicit waits used)
- ✔ Robust assertions and validations
- ✔ Solid Page Object Model with reusability and abstraction
- ✔ Screenshot capture:
  - 📸 On failed steps via `ScreenshotOnFailureDecorator`
  - 🧨 On failed Cucumber scenarios via `CucumberHooks`
- ✔ Logging with **Log4j2**
- ✔ Configurable test environments (`dev`, `staging`, etc.)
- ✔ Cross-browser support (`Chrome`, `Firefox`) via WebDriverManager
- ✔ Flexible execution using Maven and `-D` properties

---

## 📂 Project Structure (Cucumber-Enhanced)

```
src 
├── logs 					        # Daily log files (auto-generated via Log4j2 RollingFile) 
├── screenshots 				        # Screenshots for failed test cases 
├── test 
│ ├── java 
│ │ └── com.testing 
│ │   ├── context 
│ │   │ └── ScenarioContext.java  		        # Holds a thread-local WebDriver instance for Cucumber scenar 
│ │   ├── driver 
│ │   │ ├── DriverFactory.java 			        # Creates instances of WebDriver 
│ │   │ └── DriverSingleton.java  		        # Implementation for WebDriver management 
│ │   ├── hooks 
│ │   │ └── CucumberHooks.java  		        # Cucumber hooks to manage browser lifecycle around each scenario  
│ │   ├── model 
│ │   │ ├── ComposeEmail.java 			        # Represents email content for composing drafts 
│ │   │ └── EmailMetadata.java 			        # Email details for assertions and comparisons 
│ │   ├── pages 
│ │   │ ├── BasePage.java 			        # Abstract class for child pages 
│ │   │ ├── LoginPage.java 			        # Handles login page interactions 
│ │   │ └── MailboxPage.java 			        # Main page logic (delete, spam, send, logout, etc.)
│ │   ├── runners                       # Runners for Cucumber features
│ │   │ ├── CucumberChromeSmokeRunner.java 
│ │   │ ├── CucumberFirefoxRegressionRunner.java  
│ │   │ └── CucumberTestRunner.java  		        
│ │   ├── steps                                   # Cucumer steps definition
│ │   │ ├── CommonSteps.java			         
│ │   │ ├── DeleteEmailSteps.java
│ │   │ ├── MarkAsSpamSteps.java 	         
│ │   │ └── SendDraftMailSteps.java		        
│ │   └── utils 
│ │     │  └── decorators 			
│ │     │     ├── PerformanceTimerDecorator.java 	# Logs the execution time of each web action
│ │     │     ├── ScreenshotOnFailureDecorator.java     # Captures a screenshot whenever an exception occurs
│ │     │     └── WebActionDecoratorInterface.java 	# Interface for web interaction decorators 
│ │     ├── ConfigReader.java 			        # Reads configuration settings from a properties file
│ │     ├── EnhancedWebAction.java 		        # Wrapper for WebDriver actions (with highlight, logging, etc)
│ │     ├── EnvironmentManager.java 	                # Centralized environment manager
│ │     ├── ScreenshotUtil.java 	                # Take screenshot 
│ │     └── WebActions.java 	                        # Low-level web interaction methods such as typing into fields, waiting, etc 
│ ├──- resources
│ │  │   └── META-INF.services 			
│ │  │   │   └── org.testng.ITestNGListener
│ │  │   └── featuress	                      # Cucumber feature files
│ │  │       ├── delete_email.feature
│ │  │       ├── login.feature
│ │  │       ├── logout.feature
│ │  │       ├── mark_email_spam.feature
│ │  │       └── send_draft_mail.feature			         
│ │  ├── config.dev.properties 			        # Environment config for dev 
│ │  ├── config.staging.properties 		        # Environment config for staging 
│ │  ├── logback.xml 			                # logback config
│ │  ├── reportportal.properties 			# ReportPortal config 
│ │  └── log4j2.xml 				        # Log4j2 configuration for console & file output target 
target
pom.xml 					        # Maven build config and dependencies 
README.md 
```

---

## 🚀 How to Run (Cucumber)

### ▶️ Run Cucumber Scenarios

Make sure you have `.feature` files under `src/test/resources/features`.

Then run:

```bash
mvn test -Dcucumber.options="--tags @smoke"
```
Or to run specific runner file :

```bash
mvn test -Dtest=CucumberFirefoxRunner
```

Or to run everything:

```bash
mvn test
```

> Feature files are mapped to Java step definitions in the `steps/` package. Screenshots are captured automatically if a scenario fails.


## ✨ Features Summary

- ✅ Cucumber BDD integration
- ✅ Automatic screenshots on step/action/scenario failure
- ✅ Decorator pattern for modular WebDriver enhancements
- ✅ Element highlighting and performance logging
- ✅ Environment and browser parameterization
- ✅ Logging, screenshots, and report portal integration
- ✅ Page Object Model architecture

---

## 🧩 ReportPortal Setup

To enable ReportPortal integration, configure:
```
src/test/resources/reportportal.properties
```

Screenshots and log entries are uploaded automatically during failures.

---

## 📸  Screenshot


## 🛠️ Tech Stack

- **Java 23**
- **Selenium WebDriver**
- **TestNG + Cucumber**
- **Maven**
- **Log4j2 (logging)**
- **Page Object Model**
- **ReportPortal**
- **WebDriverManager**

---

## 👨‍💻 Author
**MCaius**  
[GitHub](https://github.com/MCaius/) 