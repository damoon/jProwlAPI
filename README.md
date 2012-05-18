JProwlAPI
=========

Improvements
------------
With this library you can send messages to Prowl (ProwlCLient) and notify my android (NmaClient).

Examples
--------
### ProwlClient
    ProwlClient client = new ProwlClient("prowlApikey", "applicationName");

### Notify my android		
    NmaClient client = new NmaClient("nmaApikey", "applicationName");

### send Message (both)
    Event eventMessage = new Event("eventName", "messageText");
    client.pushEvent(eventMessage);
