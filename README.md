FinancialRecorder
=================
This is a financial tools which can be used to record down the financial infomation for some group activities.

----------------------------Features(High)---------------------------------
1. User Registration.
2. User can create a activity group, and this person is the CEO of this group, and point at one person as CFO(can be the same person)
3. As a user, I can join the group and be a member of this group.
4. As a CEO, I can send an activity request to the group members.
5. As a user, I can accept the activity request.
6. As a CFO, I can manage the group's financial:
     - cash in for a member.
     - fill in the cost of each activity. (system will calculate: cost per head = total fee/total participate members). (可能要考虑支持member的权重，比如夫妻两个人的费用算到一个人头上)
7. As a user, I can see my account information, and cost of each activity.
8. As a user, I want to be notified when my account has little money.
9. To be continued...

----------------------------Features(Later)----------------------------------
1. one person can join multiple groups, and share one account.
2. To be continued...


-------------------------------Design--------------------------------------
1. Application design/framework, portal + server:
     - portal: need to support different device: PC browser, like chrome app(QS)...and ios/android app.
     - server: restful api.
2. DB design:
     - entity model:
     ...
     - simple implementation first, no cache, no non-sql.
3. API design, server's API:
     - cash in: group CFO cash in for the group member.(maybe later on it is cashed in by admin, if need to support share account between groups feature.)
     - view group's financial records(per activity).
     - fill in the cost per activity.
4. Test desgin/framework:
     ...
5. Deployment:
     options:
          - cloudfoundry.
          - google app engine.
          - ...
     issues:
          - communicate between two cloud services.(QS)
          - ...
6. Permission control
     - such as: only the CFO can cash in for each member and fill in the activity cost. normal member can only see his own account, and so on...

-------------------------------Plan----------------------------------------
1. build up the project first, github.
2. framework: spring, cxf, hibernate?, database service on cloud?.(QS)
2. entity design.
3. persistence.
4. business logic, implement the back-end server restful interface.
     - create group directly, dummy user data.
	 - ...