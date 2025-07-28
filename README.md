


## Saucedemo Automated Testing Project

This is my first independent QA automation project, completed as part of a QA course. The project focuses on testing the [Saucedemo](https://www.saucedemo.com/) demo web shop, using Java and Selenium WebDriver. It follows the Page Object Model (POM) structure to keep the code clean and maintainable.

### Project Summary

* UI test automation using **Java** and **Selenium WebDriver**
* Each web page is represented by its own class using `@FindBy` annotations (POM)
* Login functionality is tested with data from an Excel file using **Apache POI**
* Item prices are stored in an `ArrayList`, and the total is validated against the displayed value
* `try-catch` blocks are used to catch failed assertions and display clear error messages
* Covered scenarios include login, adding/removing items from the cart, and verifying totals
* Demonstrates object-oriented programming, collections, exception handling, and clean code structure

### Tools and Technologies

* Java
* Selenium WebDriver
* Apache POI (Excel data reading)
* TestNG 
* IntelliJ IDEA

### How to Run the Project

1. Clone the repository:
   `git clone https://github.com/vladanamajstorovic/SaucedemoWebSite.git`

2. Open the project in **IntelliJ IDEA**

3. Make sure **ChromeDriver** is installed and added to your system path

4. Place the Excel file with login data in the `resources` folder

5. Run the test classes from the `tests` package

### What I Learned

Through this project, I learned how to:

* Build a basic but functional automation framework in Java
* Structure tests using the Page Object Model and PageFactory
* Read data from external sources (Excel files) using Apache POI
* Validate dynamic content using collections like `ArrayList`
* Write clear and readable test code using TestNG
* Handle assertion failures and debug test results effectively

This project helped me put into practice everything I learned during the course and gave me confidence to work on real-world QA automation tasks.

---

**Author:** Vladana Majstorović
**Course:** Software Testing (QA)– IT Bootcamp, 2025

---

