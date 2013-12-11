assignment2 README
1.the app and message models both have validation in model files
2.apps table is seeded in seeds.rb in config directory
3.messages_controller has index create show functions as requirement
  and also I design a html form post function, so it called new
4.use curl --data "message[username]=britney&message[content]=Hello&message[app_id]=1" http://127.0.0.1:3000/messages
  command, you can post data to the database(this application use sqlite db as default)
5.root of app is set to index, you can check it by using rake routes
6.all the front page messages applied timeago format
7.you can click on the messages in order to go the the individual message page and
  also you can going back by click on the "back" at the right-top of the message
  
App URL http://message-center.herokuapp.com/
Git URL git@heroku.com:message-center.git    