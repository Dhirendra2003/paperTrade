This is a Practice trading app made using JSP , JDBC in java . this dynamic web app is created on ECLIPSE IDE 
project PaperTrade uses Yahoo finance API for fetching realtime stock data. Link:  https://rapidapi.com/apidojo/api/yahoo-finance1

#technologies used:
1.Java -JSP & JDBC 
2.HTML , CSS & javascript 
3.MySQL database 
4.Rapid API- yahoo finance
5.Alpha vantage API - for news
6.Apache tomcat localhost - v10.1
7.AJAX

#To use this app follow these steps:
1.make new project in eclipse and copy all the files from master/ src 
2. configure apache tomcat server v.10 in your system 
3.create database named 'papertrade'
4.create 2 tables in DB - i)users - [id,uname,upwd,umail,umobile,money,stockHolded]
                          ii)trades - [id , userid,Ttype, price, quantity,stockname, totalvalue]
                          ![database representation](https://github.com/Dhirendra2003/paperTrade/assets/112932001/9f12498e-7cf7-4385-b078-80bf96a7a1ef)

5.update link to database in java codes.
6.run app on server.


Demo Images:
![Screenshot paperTrade1](https://github.com/Dhirendra2003/paperTrade/assets/112932001/879009e7-7753-42a4-a68d-229c14b2a263)
![Screenshot paperTrade2](https://github.com/Dhirendra2003/paperTrade/assets/112932001/9e8ff909-e3ad-40d8-83d5-2f1330db00e7)
![Screenshot paperTrade3](https://github.com/Dhirendra2003/paperTrade/assets/112932001/3e216894-e167-4aa4-9a8d-903bd4fbe79b)
![Screenshot paperTrade4](https://github.com/Dhirendra2003/paperTrade/assets/112932001/98959097-8b0c-4c50-b649-2a37923b1772)
![Screenshot paperTrade5](https://github.com/Dhirendra2003/paperTrade/assets/112932001/9f0aad73-43ad-4ea1-8307-ba64330f20e9)
