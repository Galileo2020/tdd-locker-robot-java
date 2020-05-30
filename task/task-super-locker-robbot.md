### super Locker Robot Tasking

#### Tasking

1. 存包

   - [ ] Given Robot管理容量均为2的两个lockers，When 存包，Then 包存入其中一个柜子
   - [ ] Given Robot管理容量均为2的两个lockers并且其中一个locker已存了一个包，When 存包，Then 两个柜子的空置率均为50%
   - [ ] Given Robot管理的lockers都满了，When 存包，Then 提示用户柜子满了

2. 取包

   - [ ] Given 正确的Ticket, When 取包, Then 成功取出对应的包
   - [ ] Given 给的Ticket是伪造的 When取包 Then 无法取包并提示无效票

