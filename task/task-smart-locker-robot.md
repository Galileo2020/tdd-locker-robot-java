### Smart Locker Robot Tasking

#### Tasking

1. 存包

   - [ ] Given Robot管理剩余容量分别为5和4的两个lockers，When 存包，Then 包存入容量为5的locker并出票
   - [ ] Given Robot管理剩余容量分别为4和5的两个lockers，When 存包，Then 包成功存入容量为5的locker且出票
   - [ ] Given Robot管理剩余容量为5的两个locker，另一个容量为4的locker，When 存包，Then 包会存入容量为5的locker且出票
   - [ ] Given Robot管理的lockers都满了，When 存包，Then 提示用户柜子满了

2. 取包

   - [ ] Given 提供正确的Ticket给Robot, When 取包, Then 成功取出容量较低的柜子里存的对应的包
   - [ ] Given 提供正确的Ticket给Robot, When 取包, Then 成功取出容量较高的柜子里存的对应的包
   - [ ] Given给的Ticket是伪造的 When取包 Then 无法取包并提示无效票
