IpAddrCounter
-------------------

This console program counts unique IPv4 addresses in the provided file. It can process very big files (100Gb+)
IP file should have IP addresses each placed on a new line. 

```
145.67.23.4
8.34.5.23
89.54.3.124
89.54.3.124
3.45.71.5
...
```
If some lines are not valid IPv4, the program will ignore them.

**Usage:**

`IpAddrCounter FILE` 
