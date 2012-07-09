FinancialRecorder
=================
This is a financial tools which can be used to record down the financial infomation for some group activities.

Feature List
---------------------------------------------
- User Registration.
- User can create a activity group, and point at one person as CFO
- As a user, I can join the group and be a member of this group.
- As a group member, I can initial an activity and notify the others in my group.(if he/she has more than one group, then he/she can choose which group may be notified.)
- As a group member, I can accept this activity, which will be notified back to the initial person.
- After this activity has finished, as a CFO, I can manage the group's financial:
	-- cash in for a member.
	-- fill in the cost of the activity. (system will calculate: cost per head = total fee/total participate members). 
- As a user, I can see my account information, and cost of each activity.
- As a user, I want to be notified when my account has little money.
- To be continued...

---------------------------------------------Enhanced Feature List---------------------------------------------
- Multiple group support, one person can join multiple groups, and share one account.
- To be continued...


Design
---------------------------------------------
- Application design/framework, portal + server:
     -- portal: need to support different device: PC browser, like chrome app(QS)...and ios/android app.
     -- server: restful api.
- DB design:
     -- entity model:
          User: id, name, password, balance.
          Group: name, CFO(user id), CEO(user id), group member(user).
          Activity: name, place, description, start time, holding time, end time, participated member.
     -- simple implementation first, no cache, no non-sql.
- API design, server's API:
     -- cash in: group CFO cash in for the group member.(maybe later on it is cashed in by admin, if need to support share account between           groups feature.)
     -- create activity: group CEO can create a activity, and it will be sent out to each group member.
     -- accept activity: group member can accept the activity.
     -- view activity: activity participator can view activity's info, including the fee.
     -- finish activity: group CFO fill in the total cost.(and may need to check if that one has participated.)
- Test desgin/framework:    

- Deployment:
	-- cloudfoundry.         
- Permission control
	-- how to do the permission control on app(QS).
    -- such as: only the CFO can cash in for each member and fill in the activity cost. normal member can only see his own account, and so on...


Plan-1 (Backend Server, done)
---------------------------------------------
- build up the project first, github. (done)
- framework: spring, cxf, hibernate, database service on cloud. (QS) (done)
- entity design, version 0.1 (done)
     -- User
     -- FinancialRecord
- persistence, Dao. (done)
- business logic, implement the back-end server restful interface. (done)
     -- simply implement it, user and financialRecord
     -- can cashin, auto-calculate the fee per user according to the financial record.

Plan-2 (Application, ongoing)
-------------------------------
- use sencha touch 2.0, mvc structure.(done)
- build a basic page such as activities list, which data is loaded from backend server on cloudfoundry.(done)
- build a simple mobile application, to have those above functions
- UI
- communication between application and server. (QS done, using JSONP)
- notify to the mobile user.(QS)



