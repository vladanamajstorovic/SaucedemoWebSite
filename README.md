This is an automated testing project for the Saucedemo demo web shop, developed in Java using Selenium WebDriver, following the Page Object Model (POM) design pattern.

✅ Project Highlights

*Selenium with Java: Automated UI tests for Saucedemo website.
*Page Object Model: Each page is represented by a separate class, with web elements defined using @FindBy annotations for better maintainability.
*Excel Data Driven Testing: Login credentials (username and password) are read from an Excel file to demonstrate data-driven testing.
*ArrayList Sum Calculation: Used ArrayList to collect item prices in the cart and verify that the displayed total matches the calculated sum.
*Exception Handling: try-catch blocks are used to handle AssertionError gracefully when an assertion fails, ensuring clear debug messages.
*Multiple Classes & Tests: The project is structured with multiple page classes and corresponding test classes, covering various flows like login, adding items to cart, and verifying totals.
*Java Concepts Applied: Implements OOP principles learned during Java training, such as encapsulation, abstraction, exception handling, and collections.

🧩 Technologies & Tools

*Java
*Selenium WebDriver
*PageFactory & @FindBy
*Apache POI for reading Excel files
*TestNG (or JUnit) for test execution and assertions
*IntelliJ IDEA as IDE

🚀 How to Run

Clone the Repository
git clone https://github.com/vladanamajstorovic/SaucedemoWebSite.git
Open in IntelliJ IDEA
Configure WebDriver
Make sure you have ChromeDriver (or other browser driver) set up in your system path.
Prepare Test Data
Ensure the Excel file with usernames/passwords is in the correct resources folder.
Run Tests
Run test classes from the tests package.

💡 Key Learning Points

*Building robust and maintainable Selenium frameworks.
*Using Page Object Model with PageFactory and @FindBy.
*Reading test data from Excel with Apache POI.
*Using Java ArrayList and streams to calculate sums and validate totals.
*Applying try-catch to handle assertion failures gracefully.
*Structuring tests and utilities following clean coding principles.

🔗 Demo Website
https://www.saucedemo.com/

✨ Author
Vladana Majstorović
