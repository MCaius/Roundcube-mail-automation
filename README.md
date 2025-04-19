# 📬 RoundCube WebMail Automation Testing Project

This project is a robust and flexible end-to-end test automation suite for the RoundCube webmail system. It's built using **Java**, **Selenium WebDriver**, **TestNG**, and follows a clean **Page Object Model (POM)** architecture. It supports multiple browsers, environments, parameterization, reusable components, daily log generation, and dynamic element highlighting during test execution.

---

## ✅ Acceptance Criteria Coverage

- ✔ Linear test scenarios with stable assertions.
- ✔ 3 core test flows implemented:
    - **Delete Email**
    - **Mark Email as Spam**
    - **Send Draft Mail**
- ✔ Custom waits and usage of WebDriver API methods (`click()`, `getText()`, `switchTo()`, etc.).
- ✔ Mix of locator strategies: `id`, `cssSelector`, `tagName`, etc.
- ✔ No auto-generated or unstable locators used.
- ✔ Full usage of **implicit and explicit waits**.
- ✔ Proper test assertions and validations in every test.
- ✔ Clean and reusable **Page Object Model** structure.
- ✔ Inheritance and abstraction using a base page (`BasePage`) and `TestBase` for test setup/teardown.
- ✔ Encapsulation respected — internal logic abstracted from test classes.
- ✔ Screenshots on failure for easier debugging.
- ✔ Flexible execution using parameters: browser, environment, and suite.
- ✔ Logging for every interaction using **Log4j2** with console & file output (daily logs).
- ✔ Elements are **highlighted during test execution** to visualize actions.

---

## 📂 Project Structure

```
src 
├── logs 								# Daily log files (auto-generated via Log4j2 RollingFile) 
├── screenshots 						# Screenshots for failed test cases 
├── test 
│ ├── java 
│ │ └── com.testing 
│ │   ├── driver 
│ │   │ └── DriverSingleton.java  		# WebDriver setup with support for Chrome & Firefox 
│ │   ├── model 
│ │   │ ├── ComposeEmail.java 			# Represents email content for composing drafts 
│ │   │ ├── EmailMetadata.java 			# Email details for assertions and comparisons 
│ │   ├── pages 
│ │   │ ├── BasePage.java 				# Abstract class for common wait and utility methods 
│ │   │ ├── LoginPage.java 				# Handles login page interactions 
│ │   │ └── MailboxPage.java 			# Main page logic (delete, spam, send, logout, etc.) 
│ │   ├── tests 
│ │   │ ├── DeleteEmailTest.java 		# Deletes email and verifies 
│ │   │ ├── LoginTest.java 				# Validates login and logout flow 
│ │   │ ├── LoginOut.java 				# Simple login-logout scenario 
│ │   │ ├── MarkAsSpamTest.java			# Marks email as spam 
│ │   │ ├── SendDraftMailTest.java 		# Composes, saves as draft, and sends email 
│ │   │ └── TestBase.java 				# Base test logic: setup, teardown, screenshots, logging 
│ │   └── utils 
│ │     ├── ConfigReader.java 			# Loads environment-specific config properties 
│ │     └── EnhancedWebActions.java 	# Wrapper for WebDriver actions (with highlight + logging) 
│ ├──- resources 
│ │  ├── config.dev.properties 			# Environment config for dev 
│ │  ├── config.staging.properties 		# Environment config for staging 
│ │  ├── all-tests.staging-chrome.xml 	# All test suite for Chrome 
│ │  ├── all-tests.staging-firefox.xml 	# All test suite for Firefox 
│ │  ├── smoke-suite.xml 				# Smoke tests 
│ │  ├── regression-suite.xml 			# Full regression suite 
│ │  └── log4j2.xml 					# Log4j2 configuration for console & file output target 
target
pom.xml 								# Maven build config and dependencies 
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
   By default: logs are saved in /logs/app-YYYY-MM-DD.log and console.

**✨ Features**

- ✅ Element highlighting when clicked or interacted with
- ✅ Screenshot capture on test failure (in /screenshots)
- ✅ Daily log file rotation (/logs)
- ✅ Configurable test environments (dev, staging)
- ✅ Cross-browser support (Chrome, Firefox via WebDriverManager)
- ✅ Flexible via -D params (perfect for CI pipelines)
- ✅ Log levels: debug, info, error, action (Log4j2)
- ✅ Clear POM structure and reusable utility actions



## 🎥 Video




https://github.com/user-attachments/assets/b677fe94-fefa-4567-9823-bf5952beada6





## 🛠️ Tech Stack

- **Java 23**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **Log4j2 (logging)**
- **Page Object Model**
- **WebDriverManager**

---

## 👨‍💻 Author

**MCaius**  
[GitHub](https://github.com/MCaius/) 
