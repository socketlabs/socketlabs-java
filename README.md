[![SocketLabs](https://www.socketlabs.com/assets/socketlabs-logo1.png)](https://www.socketlabs.com)
# [![Twitter Follow](https://img.shields.io/twitter/follow/socketlabs.svg?style=social&label=Follow)](https://twitter.com/socketlabs) [![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE) [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/socketlabs/socketlabs-java/blob/master/CONTRIBUTING.md)
<!--
[![GitHub contributors](https://img.shields.io/github/contributors/socketlabs/socketlabs-java.svg)](https://github.com/socketlabs/socketlabs-java/graphs/contributors)
-->

The SocketLabs Email Delivery Java library allows you to easily send email messages via the [SocketLabs Injection API](https://www.socketlabs.com/api-reference/injection-api/).  The library makes it easy to build and send any type of message supported by the API, from a simple message to a single recipient all the way to a complex bulk message sent to a group of recipients with unique merge data per recipient.

# Table of Contents
* [Prerequisites and Installation](#prerequisites-and-installation)
* [Getting Started](#getting-started)
* [Managing API Keys](#managing-api-keys)
* [Examples and Use Cases](#examples-and-use-cases)
* [License](#license)


<a name="prerequisites-and-installation"></a>
# Prerequisites and Installation
## Prerequisites
* A supported Java version
  * Oracle JDK 7 
  * Oracle JDK  8
  * OpenJDK 7
* A SocketLabs account. If you don't have one yet, you can [sign up for a free account](https://signup.socketlabs.com/step-1?plan=free) to get started.

## Installation
Choose your installation method - Maven w/ Gradle (recommended), Maven or Jar file.

### via Maven w/ Gradle
Add the following to your build.gradle file in the root of your project.

```
...
dependencies {
  ...
  implementation 'com.socketLabs.injectionApi:socketlabs-java:1.0.0'
}

repositories {
  mavenCentral()
}
...
```

### via Maven

```
mvn install
```

### via jar file

You can just drop the jar file in. It's a fat jar - it has all the dependencies built in.

[sendgrid-java.jar](https://github.com/socketlabs/socketlabs-java/releases/download/v1.1.0/socketlabs-java.jar)

Alternately, you can simply [clone this repository](https://github.com/socketlabs/socketlabs-java.git) directly to include the source code in your project.

<a name="getting-started"></a>
# Getting Started
## Obtaining your API Key and SocketLabs ServerId number
In order to get started, you'll need to enable the Injection API feature in the [SocketLabs Control Panel](https://cp.socketlabs.com).
Once logged in, navigate to your SocketLabs server's dashboard (if you only have one server on your account you'll be taken here immediately after logging in).
Make note of your 4 or 5 digit ServerId number, as you'll need this along with
your API key in order to use the Injection API.

To enable the Injection API, click on the "For Developers" dropdown on the top-level navigation, then choose the "Configure HTTP Injection API" option.
Once here, you can enable the feature by choosing the "Enabled" option in the
dropdown. Enabling the feature will also generate your API key, which you'll
need (along with your ServerId) to start using the API. Be sure to click the
"Update" button to save your changes once you are finished.



## Basic Message
A basic message is an email message like you'd send from a personal email client such as Outlook.
A basic message can have many recipients, including multiple To addresses, CC addresses, and even BCC addresses.
You can also send a file attachment in a basic message.

```java
import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;

var client = new SocketLabsClient(000001, "YOUR-API-KEY"); //Your SocketLabs ServerId and Injection API key

BasicMessage message = new BasicMessage();

message.setSubject("Sending A Test Message (Basic Send)");
message.setHtmlBody("<html><body><h1>Sending A Test Message</h1><p>This is the Html Body of my message.</p></body></html>");
message.setPlainTextBody("This is the Plain Text Body of my message.");

message.setFrom(new EmailAddress("from@example.com"));

//A basic message supports up to 50 recipients and supports several different ways to add recipients

// Adding To Recipients
message.getTo().add(new EmailAddress("recipient1@example.com"));
message.getTo().add(new EmailAddress("recipient2@example.com", "Recipient #2"));

// Adding CC Recipients
message.addCcEmailAddress(new EmailAddress("ccRecipients1@example.com",  "Recipient #3" ));

 // Adding Bcc Recipients
message.addBccEmailAddress("bccRecipients1@example.com");
message.addBccEmailAddress("bccRecipients2@example.com", "Recipient #2");

SendResponse response =  client.send(message);
```

## Bulk Message
A bulk message usually contains a single recipient per message
and is generally used to send the same content to many recipients,
optionally customizing the message via the use of MergeData.
For more information about using Merge data, please see the [Injection API documentation](https://www.socketlabs.com/api-reference/injection-api/#merging).
```java

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;

SocketLabsClient client = new SocketLabsClient(000001, "YOUR-API-KEY"); //Your SocketLabs ServerId and Injection API key

BulkMessage message = new BulkMessage();

message.setSubject("Sending A Test Message (Bulk Send)");
message.setHtmlBody("<html><body><h1>Sending A Test Message</h1><p>This is the Html Body of my message.</p></body></html>");
message.setPlainTextBody("This is the Plain Text Body of my message.");

message.setFrom(new EmailAddress("from@example.com"));
message.setReplyTo(new EmailAddress("replyto@example.com"));

message.getTo().add(new BulkRecipient("recipient1@example.com"));
message.getTo().add(new BulkRecipient("recipient2@example.com", "Recipient #2"));
message.getTo().add(new BulkRecipient("recipient3@example.com"));
message.getTo().add(new BulkRecipient("recipient4@example.com", "Recipient #4"));

SendResponse response =  client.send(message);
```

<a name="managing-api-keys"></a>
## Managing API Keys
For ease of demonstration, many of our examples include the ServerId (SOCKETLABS_SERVER_ID) and API
key (SOCKETLABS_INJECTION_API_KEY) directly in our code sample. Generally it is not considered a good practice
to store sensitive information like this directly in your code. Depending on
your project type, we recommend either storing your credentials using Environment Variables. For more
information please see: [Using Environment Variables](https://docs.oracle.com/javase/tutorial/essential/environment/env.html)


<a name="examples-and-use-cases"></a>
# Examples and Use Cases
In order to demonstrate the many possible use cases for the SDK, we've provided
an assortment of code examples. These examples demonstrate many different
features available to the Injection API and SDK, including using templates
created in the [SocketLabs Email Designer](https://www.socketlabs.com/blog/introducing-new-email-designer/), custom email headers, sending
attachments, sending content that is stored in an HTML file, advanced bulk
merging, and even pulling recipients from a datasource.


### [Basic send async example](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicAsync.java)
This example demonstrates a Basic Send done asynchronously.

### [Basic send example](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicSend.java)
This example demonstrates a Basic Send done synchronously.

### [Basic send complex example](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicSendComplexExample.java)
This example demonstrates many features of the Basic Send, including adding multiple recipients, adding message and mailing id's, and adding an embedded image.

### [Basic send from HTML file](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicSendFromHtmlFile.java)
This example demonstrates how to read in your HTML content from an HTML file
rather than passing in a string directly.

### [Basic send from SocketLabs API Template](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicSendWithApiTemplate.java)
This example demonstrates the sending of a piece of content that was created in the
SocketLabs Email Designer. This is also known as the [API Templates](https://www.socketlabs.com/blog/introducing-api-templates/) feature.

### [Basic send with specified character set](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicSendWithASCIICharset.java)
This example demonstrates sending with a specific character set.

### [Basic send with file attachment](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicSendWithAttachment.java)
This example demonstrates how to add a file attachment to your message.

### [Basic send with custom email headers](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicSendWithCustomHeaders.java)
This example demonstrates how to add custom headers to your email message.

### [Basic send with embedded image](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicSendWithEmbeddedImage.java)
This example demonstrates how to embed an image in your message.

### [Basic send with a web proxy](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/BasicSendWithProxy.java)
This example demonstrates how to use a proxy with your HTTP client.


### [Basic send with invalid file attachment](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/invalid/BasicSendWithAttachment.java)
This example demonstrates the results of attempting to do a send with an invalid attachment.

### [Basic send with invalid from address](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/invalid/BasicSendWithInvalidFrom.java)
This example demonstrates the results of attempting to do a send with an invalid from address.

### [Basic send with invalid recipients](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/basic/invalid/BasBasicSendWithInvalidRecipientsicSend.java)
This example demonstrates the results of attempting to do a send with invalid recipients.


### [Bulk send with multiple recipients](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/bulk/BulkSend.java)
This example demonstrates how to send a bulk message to multiple recipients.

### [Bulk send with complex merge including attachments](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/bulk/BulkSendComplexExample.java)
This example demonstrates many features of the `BulkMessage()`, including
adding multiple recipients, merge data, and adding an attachment.

### [Bulk send with recipients pulled from a datasource](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/bulk/BulkSendFromDataSourceWithMerge.java)
This example uses a mock repository class to demonstrate how you would pull
your recipients from a database and create a bulk mailing with merge data.

### [Bulk send with Ascii charset and special characters](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/bulk/BulkSendWithASCIICharsetMergeData.java)
This example demonstrates how to send a bulk message with a specified character
set and special characters.

### [Bulk send with merge data](https://github.com/socketlabs/socketlabs-java/tree/master/examples/src/main/java/examples/bulk/BulkSendWithMergeData.java)
This example demonstrates how to send a bulk message to multiple recipients with
unique merge data per recipient.


<a name="license"></a>
# License
The SocketLabs.EmailDelivery library and all associated code, including any code samples, are [MIT Licensed](https://github.com/socketlabs/socketlabs-java/blob/master/LICENSE.MD).
