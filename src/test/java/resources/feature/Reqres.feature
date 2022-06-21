
  Feature: Testing different users on the Reqres Application

    Scenario: Check if the user application is accessed by the users
      Given I am on Homepage of application reqres
      When User send a GET Request to list endpoint users
      Then User can get back a valid status code 200 of users

    Scenario: Check if I can add  user in the application
      Given I am on Homepage of application reqres
      When User can create new user using POST method in products application
      Then User can get back a valid status code 201 of users


    Scenario: Check if I can Delete  user in the application
      Given I am on Homepage of application reqres
      When User can Delete new product using DELETE method in products application
      Then User can get back a valid status code 204 of users
      And User verify that the product is deleted successfully from product

    Scenario: User  verify following application is accessed by the users
      Given I am on Homepage of application reqres
      When User send a GET Request to list endpoint users
      Then I validate page "1"
      And  I validate per_page "6"
      And  I validate totalID "2"
      And  I validate name of ID "Eve"
      And  I validate list of data 6
      And  I validate avatar "https://reqres.in/img/faces/11-image.jpg"
      And  I validate support url  "http://dummy.restapiexample.com/"
      And  I validate support text "To keep ReqRes free, contributions towards server costs are appreciated!"

