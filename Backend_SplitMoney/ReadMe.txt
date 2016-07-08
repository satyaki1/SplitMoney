List Of REST APIs designed:


List all groups:[Use Post to create]
http://localhost:8080/SplitMoney/groups/

List a particular group
http://localhost:8080/SplitMoney/groups/{groupId}

List all Users for a group: [Use Post to create]
http://localhost:8080/SplitMoney/groups/{groupId}/users

List a particular user in a group:
http://localhost:8080/SplitMoney/groups/{groupId}/users/{userId}

List all expenditures in a group: [Use POST to create]
http://localhost:8080/SplitMoney/groups/{groupId}/expenditures

List all expenditures for a user in a group:
http://localhost:8080/SplitMoney/groups/{groupId}/expenditures/{userId}
