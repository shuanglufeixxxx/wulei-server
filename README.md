# Setup local MySQL Server
install MySQL Server 5.7
add 'export PATH="/usr/local/mysql/bin:$PATH"' to ~/.bash_profile
# Setup local database
$ mysql -u root -p
`>` create user 'wulei_server_admin' identified by 'atrx';
`>` create database wulei character set utf8mb4 collate utf8mb4_general_ci;
`>` grant all on wulei.* to 'wulei_server_admin';
# Run project firsttime locally
undo comment all @Bean annotations near 'CommandLineRunner' in WuleiApplication.java
$ gradle bootRun
make all @Bean annotations near 'CommandLineRunner' in WuleiApplication.java as comments
# Run project locally
$ gradle bootRun
# Setup Azure MySQL Server
https://docs.microsoft.com/en-us/azure/mysql/
# Setup Azure database
$ mysql -h `<azure-database-domain>` -u `<root-username>` -p
`>` create user `<username>` identified by `<password>`;
`>` create database wulei character set utf8mb4 collate utf8mb4_general_ci;
`>` grant all on wulei.* to `<username>`;
# Deploy to Azure
$ gradle clean
$ gradle bootJar -x test
https://docs.microsoft.com/en-us/azure/spring-cloud/
hint: builded jar is under ./build/libs