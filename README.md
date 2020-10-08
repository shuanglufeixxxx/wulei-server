# Setup MySQL Server
install MySQL Server 5.7
add 'export PATH="/usr/local/mysql/bin:$PATH"' to ~/.bash_profile
# Setup database
$ mysql -u root -p
> create user 'wulei_server_admin' identified by 'atrx';
> create database wulei character set utf8mb4 collate utf8mb4_general_ci;
> grant all on wulei.* to 'wulei_server_admin';
# Run project firsttime
undo comment all @Bean annotations near 'CommandLineRunner' in WuleiApplication.java
$ gradle bootRun
make all @Bean annotations near 'CommandLineRunner' in WuleiApplication.java as comments
# Run project
$ gradle bootRun