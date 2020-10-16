# codingchallenge

To run the app go to Terminal and run

```mvn spring-boot:run```

The program runs when you hit http://server:port (localhost:8080)

Please open the docs folder and use the postman collection to validate the services.

Application consists of three endpoints.

```GET http://localhost:8080/coins```
This gets the current state of available coins.

```POST http://localhost:8080/coins```
This helps to configure coins as needed.

```http://localhost:8080/change/5?allowLeastCoins=false```
This provides the change for the given avalid amount.Set ```allowLeastCoins``` to ```true``` to get the most coins.


