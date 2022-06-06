# 100ms-assignment


## Description  

I have used Java, Selenium, PageFactory (to demonstrate page Object Model), and Maven Framework to complete this assignment.  

## Java Version  
Take latest release of AdoptOpenjdk (LTS 11, Hotspot) from here: [AdoptOpenJDK](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)  

## Setting up JAVA_HOME  
Go to your terminal and run following command ls -lart, you will see  .zshrc if not the create with following command vi .zshrc and then save the file by pressing esc then write :q and press enter.  
  
Open .zshrc  in terminal with command vi .zshrc append the following command in the file  
  
```
export JAVA_HOME=$(/usr/libexec/java_home)  
```
  
Now run following command to apply the settings `source .zshrc`  
To verify, run the following command echo $JAVA_HOME and if it displays the value as following  
  
```
/Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home  
```

## Maven  
install maven using homebrew command $ `brew install maven`  

## Executables  

Here is the command you can use -  
  
```
mvn clean test -DmyBrowser="chrome" -Dmode="gui" -Dresolution="laptop" -Denv="prod"  
```

-DmyBrowser - Can have following values ["chrome", "firefox", "safari"]  
-Dmode - Can have following values ["gui", "headless"]  
-Dresolution - Can have following values ["hd", "laptop", "tablet-landscape", "tablet-portrait", "mobile"]  
-Denv - Can have following values ["prod", "stage", "qa"]  

You can also import the maven project into an IDE and execute the `src/TestSuites/testng.xml` as a TestNG suite.  

## Execution Reports  

You can find the execution reports in the HTML format in the "allure-results"  

### Allure Installation  
[Install Allure](https://docs.qameta.io/allure/#_mac_os_x)  

### Generate Allure Report  

```
allure serve ./allure-results  
```