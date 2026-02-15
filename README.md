## Messages

#### System Message
* A message of the type 'system' passed as input. 
* This role typically provides high-level instructions for the conversation. 
* For example, you might use a system message to instruct the generative to behave like a certain character or to provide answers in a specific format.
#### User Message
* A message of the type 'user' passed as input Messages with the user role are from the end-user or developer. 
* They represent questions, prompts, or any input that you want the generative to respond to
#### Assistant Message
* Lets the generative know the content was generated as a response to the user. 
* This role indicates messages that the generative has previously generated in the conversation. 
* Message from the LLM Modal that is going to consider as Assistant Message  
#### ToolResponse Message / Functional Message 
* The ToolResponseMessage class represents a message with a function content in a chat application.

## Defaults
* DefaultChatClientBuilder is a builder class for creating a ChatClient.
  It provides methods to set default values for various properties of the ChatClient.
* `defaultSystem(), defaultUser(), defaultAdvisior()`

## Prompt Templates

* Using the prompt templates makes prompt construction easy.
* Makes Prompt reusable and maintainable
* Keeps the logic and the prompts cleanly seperated.
* It supports parameterized placeHolders
* Prompt templates can be store in `.st` file under resource folder
* Using `@Value()` providing path of the .st file 

`    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    Resource userPromptTemplate;`