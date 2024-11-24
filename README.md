# Dmoney API Automation using RestAssured
## Overview
In this project, using Rest Assured API has been automated to create some users, Login in with their valid credentials, and perform some transactions between created different kinds of users (Agent,Customers, Merchants) as well as checking the balance

## Steps are covered :
1. Login by admin (admin@roadtocareer.net/1234)
2. Create two new customers and an agent
3. Give 2000 tk from the System account to the newly created agent
4. Deposit 1500 tk to a customer from the agent account
5. Withdraw 500 tk by the customer to the agent
6. Send money 500 tk to another customer
7. Payment of 100 tk to any merchant by the recipient customer
8. Check the balance of the recipient customer

## Prerequisites :
- JDK(Updated Version)
- IntelliJ IDEA
- Gradle

## Tools & Framework :
- Selenium
- TestNG
- Allure
- Rest Assured

## Generate Allure report :
- allure generate allure-results
- allure serve allure-results

## Allure Reports :

![over_all_result_allure](https://github.com/user-attachments/assets/92089427-74c3-4303-9412-8665ad458b85)

![behaviour_allure](https://github.com/user-attachments/assets/d4955f4a-2b95-4d8d-8388-01e1d9191909)



