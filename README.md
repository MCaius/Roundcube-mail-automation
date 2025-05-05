
# 📊 ReportPortal Integration Branch

This branch is dedicated to integrating the test automation framework with **ReportPortal.io**, enabling real-time test reporting, logging, and artifact (e.g., screenshots) uploads during execution. It includes configuration, dependencies, and utility classes specifically tailored to support ReportPortal's features via TestNG and Logback.

---

# 📬 RoundCube WebMail Automation Testing Project

This project is a robust and flexible end-to-end test automation suite for the RoundCube webmail system. It's built using **Java**, **Selenium WebDriver**, **TestNG**, and follows a clean **Page Object Model (POM)** architecture. It supports multiple browsers, environments, parameterization, reusable components, daily log generation, and dynamic element highlighting during test execution.

---

## ✅ Test Automation Architecture & Features

- ✔ Linear, readable test scenarios with stable assertions
- ✔ 3 core test flows implemented:
  - **Delete Email**
  - **Mark Email as Spam**
  - **Send Draft Mail**
- ✔ Custom waits and usage of WebDriver API (`click()`, `getText()`, `switchTo()`, etc.)
- ✔ Mix of locator strategies: `id`, `cssSelector`, `tagName`, etc.
- ✔ Avoidance of unstable or auto-generated locators
- ✔ Full support for **explicit waits** (no implicit waits used)
- ✔ Robust assertions and validations in all tests
- ✔ Solid Page Object Model with reusability and abstraction
- ✔ Shared logic moved to a common `BasePage` and `TestBase` structure
- ✔ Usage of decorators:
  - 📸 **ScreenshotOnFailureDecorator** — for failure screenshots
  - ⏱ **PerformanceTimerDecorator** — to log action durations
- ✔ Elements are **highlighted during interactions** for visibility
- ✔ Logging with **Log4j2** to both console and rolling log files
- ✔ Configurable test environments (`dev`, `staging`, etc.)
- ✔ Cross-browser support (`Chrome`, `Firefox`) via WebDriverManager
- ✔ Runs are flexible via `-D` system properties (e.g., `browser`, `env`, `suite`)


---

## 📂 Project Structure

```
src 
├── logs 					        # Daily log files (auto-generated via Log4j2 RollingFile) 
├── screenshots 				        # Screenshots for failed test cases 
├── test 
│ ├── java 
│ │ └── com.testing 
│ │   ├── driver 
│ │   │ ├── DriverFactory.java 			        # Creates instances of WebDriver 
│ │   │ └── DriverSingleton.java  		        # Implementation for WebDriver management 
│ │   ├── listeners 
│ │   │ └── ScreenshotTestListener.java  		# Listens to test events and uplods screenshot  
│ │   ├── model 
│ │   │ ├── ComposeEmail.java 			        # Represents email content for composing drafts 
│ │   │ ├── EmailMetadata.java 			        # Email details for assertions and comparisons 
│ │   ├── pages 
│ │   │ ├── BasePage.java 			        # Abstract class for child pages 
│ │   │ ├── LoginPage.java 			        # Handles login page interactions 
│ │   │ └── MailboxPage.java 			        # Main page logic (delete, spam, send, logout, etc.) 
│ │   ├── tests 
│ │   │ ├── DeleteEmailTest.java 		        # Deletes email and verifies 
│ │   │ ├── LoginTest.java 			        # Validates login and logout flow 
│ │   │ ├── LoginOut.java 			        # Simple login-logout scenario 
│ │   │ ├── MarkAsSpamTest.java			        # Marks email as spam 
│ │   │ ├── SendDraftMailTest.java 		        # Composes, saves as draft, and sends email 
│ │   │ └── TestBase.java 			        # Base test logic: setup and teardown 
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
│ │       └── META-INF.services 			
│ │          └── org.testng.ITestNGListener 				         
│ │  ├── config.dev.properties 			        # Environment config for dev 
│ │  ├── config.staging.properties 		        # Environment config for staging 
│ │  ├── all-tests.staging-chrome.xml 	                # All test suite for Chrome 
│ │  ├── all-tests.staging-firefox.xml 	                # All test suite for Firefox 
│ │  ├── smoke-suite.xml 			        # Smoke tests 
│ │  ├── regression-suite.xml 			        # Full regression suite
│ │  ├── logback.xml 			                # logback config
│ │  ├── reportportal.properties 			# ReportPortal config 
│ │  └── log4j2.xml 				        # Log4j2 configuration for console & file output target 
target
pom.xml 					        # Maven build config and dependencies 
README.md 
```

---

## 🚀 How to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/MCaius/roundcube-webmail-automation.git
   ```

2. **Configure Environments**:
   ```
   src/test/resources/config.dev.properties
   src/test/resources/config.staging.properties
   ```
   **Example**:
   Configure your environment values:
   ```
   baseUrl=http://your-webmail-url.com
   username=your-username
   password=your-password
   ```

3. **Run Tests with Maven**:
   ```bash
   mvn test
   ```
   Use Maven system properties to pass browser, environment, or a specific test class.

   **▶️ Example: Run one test**
   ```bash
   mvn test -Dbrowser=chrome -Denv=staging -Dtest=com.testing.tests.LoginTest
   ```
   **▶️ Example: Run all in Firefox on dev**
   ```bash
   mvn test -Dbrowser=firefox -Denv=dev -DsuiteXmlFile=all-tests.staging-firefox.xml
   ```
   **▶️ Example: Run the full regression suite**
   ```bash
   mvn test -Dbrowser=chrome -Denv=staging -DsuiteXmlFile=regression-suite.xml
   ```
   Logs are saved in the **logs** folder and screenshots in the **screenshots** folder

## ✨ Features

- ✅ Element highlighting to visualize test actions
- ✅ Automatic screenshots on failure (`/screenshots` folder)
- ✅ Daily log rotation with separate log files (`/logs`)
- ✅ Decorator pattern for enhanced WebDriver actions (extensible & modular)
- ✅ Performance timing and logging for action durations
- ✅ Environment-based configuration support
- ✅ Cross-browser execution (Chrome, Firefox)
- ✅ `-D` parameter support for CI/CD compatibility
- ✅ Log4j2 logging with customizable levels: `debug`, `info`, `error`, `action`
- ✅ Clean project structure following Page Object Model
- ✅ Full support for S.O.L.I.D principles and design patterns (Singleton, Factory, Decorator)
- ✅ **Report Portal integration** 

### 🧩 ReportPortal Setup

To enable ReportPortal integration, make sure to configure your ReportPortal credentials and project settings inside:
```
src/test/resources/reportportal.properties
```



## 🎥 Video





https://github.com/user-attachments/assets/a687a70e-b397-402d-9a0c-59a789e66636







## 🛠️ Tech Stack

- **Java 23**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **Log4j2 (logging)**
- **Logback (for ReportPortal)**
- **Page Object Model**
- **WebDriverManager**
- **ReportPortal (real-time test reporting)**

---

## 👨‍💻 Author

**MCaius**  
[GitHub](https://github.com/MCaius/) 
