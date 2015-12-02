Distributed Cache Server
========================

$ mvn clean package


# Cache Server endpoints: 
Server_A => http://localhost:3000/cache/
Server_B => http://localhost:3001/cache/
Server_C => http://localhost:3002/cache/


# on Local Machine
$ ./bin/run_server_A.sh
$ ./bin/run_server_B.sh 
$ ./bin/run_server_C.sh 