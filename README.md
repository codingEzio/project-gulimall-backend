
## Prep

> macOS

#### Tools

> Vagrant -> Docker -> \[MySQL, Redis\]

1. The Foundation *Vagrant*

    > Almost all the tools were installed to the VM, including *Docker*

    - Get started

    ```bash
    brew install vagrant    # use the GUI way if you prefer

    vagrant --version       # check if installed correctly

    vagrant init centos/7   # config file

    # cd the where the Vagrantfile file lies in
    vagrant up              # download, config and boot
    vagrant ssh             # ssh into the virtual machine
    ```

    - Networking

        > To make the VM like just another physical PC in your LAN

    ```ini
    # Edit the Vagrantfile
    config.vm.network "public_network"

    # Run `vagrant up`
    # You shall choose the one you used to connect to the Internet
    ```

2. Docker

    > Run under the user `root`

    ```bash
    # 1. Remove existing docker just in case any problems arise
    # https://docs.docker.com/engine/install/centos/#uninstall-old-versions
    yum remove docker-engine \
        docker-common \
        docker docker-latest \
        docker-client docker-client-latest \
        docker-logrotate docker-latest-logrotate  \


    # 2. Let yum know where to find Docker to install
    # https://docs.docker.com/engine/install/centos/#install-using-the-repository

    yum install -y yum-utils
    yum-config-manager \
        --add-repo \
        "https://download.docker.com/linux/centos/docker-ce.repo"

    yum install docker-ce docker-ce-cli containerd.io docker-compose-plugin


    # 3. Configure mirrors Docker images for faster fetching
    # https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
    mkdir -p /etc/docker
    touch    /etc/docker/daemon.json
    echo '{"registry-mirrors": ["https://22diassb.mirror.aliyuncs.com"]}' > /etc/docker/daemon.json


    # 4. Configure Docker to run when the OS boots
    systemctl enable docker    # add to 'run when OS boots' list


    # 5. Run
    systemctl start docker
    ```

3. MySQL

    > Run under the user `root`

    - Get started

    ```bash
    # 1. Get MySQL
    docker pull mysql:5.7
    docker images | grep mysql

    docker container ls
    docker container stop mysql5dot7
    docker container rm   mysql5dot7


    # 2. Run MySQL
    docker run -p 3306:3306 --name mysql5dot7 \
        -v /mydata/mysql/log:/var/log/mysql \
        -v /mydata/mysql/data:/var/lib/mysql \
        -v /mydata/mysql/conf:/etc/mysql \
        -e MYSQL_ROOT_PASSWORD=root \
        -d mysql:5.7


    # 3. For Vagrant
    # Choose the network interface which is in the same LAN as your host


    # 4. For Vagrantfile
    # Exposing Docker-in-VM-Vagrant the port to the host
    config.vm.network "forwarded_port", guest: 3306, host: 3306, host_ip: "127.0.0.1"


    # 5. Connect to MySQL-in-Docker-in-Vagrant
    # HOST      VM LAN Addr
    # PORT      Host (VM:Docker:Host) (guest-3306<-3306 : 3306  host:3306)
    # USER      root
    # PASSWORD  root (-e MYSQL_ROOT_PASSWORD=root)
    ```

    - Configuration

        - Ready to edit the config for MySQL 5.7 in VM (<- Docker container)

        ```bash
        # [VIRTUAL MACHINE]     [DOCKER CONTAINER]
        # /mydata/mysql/log     /var/log/mysql
        # /mydata/mysql/data    /var/lib/mysql
        # /mydata/mysql/conf    /etc/mysql
        ```

        - Edit MySQL config ( `sudo vi /mydata/mysql/conf/my.cnf` )

        ```ini
        [client]
        default-character-set=utf8

        [mysql]
        [client]
        default-character-set=utf8

        [mysql]
        default-character-set=utf8

        [mysqld]
        init_connect='SET collation_connection = utf8_unicode_ci'
        init_connect='SET NAMES utf8'
        character-set-server=utf8
        collation-server=utf8_unicode_ci
        skip-character-set-client-handshake
        skip-name-resolve
        ```

        - Reload new configuration

        ```bash
        docker restart mysql5dot7
        ```

        - Check new configuration

        ```bash
        docker exec -it mysql5dot7 /bin/bash     # VM
        cat /etc/mysql/my.cnf                    # MySQL container
        ```

4. Redis

    > Run under the user `root`

    - Get started

    ```bash
    # 1. Get Redis
    docker pull redis:6.0.16
    docker images | grep redis

    docker container ls
    docker container stop redis6dot16
    docker container rm   redis6dot16


    # 2. Initialization before running
    # Mapping the config in VM into the Redis container
    mkdir -p /mydata/redis/conf
    touch /mydata/redis/conf/redis.conf


    # 3. Run Redis
    docker run -p 6379:6379 --name redis6dot16 \
        -v /mydata/redis/data:/data \
        -v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
        -d redis:6.0.16 \
        redis-server /etc/redis/redis.conf
    ```

    - Configuration

    ```bash
    # 1. Edit configuration (Redis persistence)
    echo 'appendonly yes' > /mydata/redis/conf/redis.conf


    # 2. Check configuration
    docker exec -it redis6dot16 /bin/bash    # VM
    cat /etc/redis/redis.conf                # Redis container
    ```

-----

## References

#### Tools

1. Vagrant

    ```bash
    vagrant status

    vagrant suspend     # sleep
    vagrant up          # boot

    vagrant halt        # shutdown
    vagrant reload      # reboot
    ```

2. Docker

    ```bash
    docker container ps -a
    docker container restart mysql5dot7

    docker exec -it mysql5dot7 /bin/bash
    ```
