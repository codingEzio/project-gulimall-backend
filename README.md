
## Prep

> macOS

#### Tools

> With the help of
>
>> *Docker Port-mapping* (`.. run -p`<u>`3306:`</u>`3306 ..`) <br/>
>> *Vagrantfile* (`config.vm.network "forwarded_port", guest:`<u>`3306,`</u>***`host: 3306`***)
>
>
> You can connect to them using `localhost:3306` like they are in the host machine
>> If you also have an app which uses port `3336`, just change the `host: PORT` in *Vagrantfile*
>
> To make the *MySQL* and *Redis* to run when you boot up the VM, add
>> `docker update mysql5dot7 --restart=always` <br/>
>> `docker update redis6dot16 --restart=always`

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
        default-character-set=utf8

        [mysqld]
        init_connect='SET NAMES utf8'
        init_connect='SET collation_connection = utf8_unicode_ci'

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

5. Maven

    > AliYun mirror (for faster download) and use JDK 1.8 to compile

6. Editors

    > Plugins to install

    - IDEA: *Lombok*, *MyBatisX*
    - VS Code: whatever suits you

#### Data

> I'm using [mycli](https://github.com/dbcli/mycli/) <small>(`mycli -u root -h localhost`)</small> and [BeeKeeper](https://www.beekeeperstudio.io/get) to manage the databases

- Create the database

    > Reference for the [command](https://dba.stackexchange.com/questions/76788/create-a-mysql-database-with-charset-utf-8/76789#76789), the *why* for [`utf8mb4`](https://stackoverflow.com/questions/30074492/what-is-the-difference-between-utf8mb4-and-utf8-charsets-in-mysql/30074553#30074553) and [`utf8mb4_unicode_ci`](https://stackoverflow.com/questions/1036454/what-are-the-differences-between-utf8-general-ci-and-utf8-unicode-ci/1036459#1036459)

    ```sql
    CREATE DATABASE IF NOT EXISTS gulimall_pms_product
        DEFAULT CHARACTER SET utf8mb4
        DEFAULT COLLATE utf8mb4_unicode_ci ;

    CREATE DATABASE IF NOT EXISTS gulimall_oms_order
        DEFAULT CHARACTER SET utf8mb4
        DEFAULT COLLATE utf8mb4_unicode_ci ;

    CREATE DATABASE IF NOT EXISTS gulimall_sms_salepromo
        DEFAULT CHARACTER SET utf8mb4
        DEFAULT COLLATE utf8mb4_unicode_ci ;

    CREATE DATABASE IF NOT EXISTS gulimall_ums_user
        DEFAULT CHARACTER SET utf8mb4
        DEFAULT COLLATE utf8mb4_unicode_ci ;

    CREATE DATABASE IF NOT EXISTS gulimall_wms_logistics
        DEFAULT CHARACTER SET utf8mb4
        DEFAULT COLLATE utf8mb4_unicode_ci ;
    ```

- Load the schema

    >  `cd` to where the `.sql` files are and get `mycli` installed (reference to [`source`](https://github.com/dbcli/mycli/issues/108#issuecomment-131845477))

    ```sql
    USE    gulimall_pms_product       ;
    source gulimall_pms_product.sql   ;

    USE    gulimall_oms_order         ;
    source gulimall_oms_order.sql     ;

    USE    gulimall_sms_salepromo     ;
    source gulimall_sms_salepromo.sql ;

    USE    gulimall_ums_user          ;
    source gulimall_ums_user.sql      ;

    USE    gulimall_wms_logistics     ;
    source gulimall_wms_logistics.sql ;
    ```

#### Scaffold

> Remove the `.git` folder before you include or start using these

###### Backend

> Backend: [*renren-fast*](https://gitee.com/renrenio/renren-fast) <br/>

- Create the database

    ```sql
    CREATE DATABASE IF NOT EXISTS gulimall_admin_renrenfast
        DEFAULT CHARACTER SET utf8mb4
        DEFAULT COLLATE utf8mb4_unicode_ci ;
    ```

- Load the data from the `.sql` included in the [repository](https://gitee.com/renrenio/renren-fast/tree/master/db)

    >  `cd` to where the `.sql` files are and get `mycli` installed (reference to [`source`](https://github.com/dbcli/mycli/issues/108#issuecomment-131845477))

    ```sql
    use    gulimall_admin_renrenfast     ;
    source gulimall_admin_renrenfast.sql ;
    ```

- Run

    ```bash
    mvn clean
    mvn install

    # Now you can run the start up the frontend Vue.js project
    mvn spring-boot:run
    ```

###### Frontend

> Frontend: [*renren-fast-vue*](https://gitee.com/renrenio/renren-fast-vue)

- Make sure you already have the backend server running

    ```bash
    yarn install

    # 1. The name and the password are both 'admin'
    # 2. The captcha was generated by the backend server
    yarn run dev
    ```

#### Code Generation

> Get [renren-generator](https://gitee.com/renrenio/renren-generator) and fix basic dep issues

###### Snippet

- Component 👐 Database

    ```java
    /*
    gulimall-coupon     gulimall_sms_salepromo
    gulimall-member     gulimall_ums_user
    gulimall-order      gulimall_oms_order
    gulimall-product    gulimall_pms_product
    gulimall-ware       gulimall_wms_logistics
    */
    ```

###### Procedure

1. Make copies based on the template

    ```bash
    ORIG_CONF_APP='template.application.yml'
    ORIG_CONF_GEN='template.generator.properties'

    # Then edit the details by your own
    for f in application-{coupon,member,order,product,ware}.yml;
        do cp ${ORIG_CONF_APP} $f;
    done

    # Then edit the details by your own
    for f in generator-{coupon,member,order,product,ware}.properties;
        do cp ${ORIG_CONF_GEN} $f;
    done
    ```

2. Run the generation service

    > Modify the directory in correspondence with yours

    ```bash
    PROJ_ROOT="/Users/mac/dev/ytb-projects-gulimall"
    COMP="coupon"

    cd "${PROJ_ROOT}/renren-generator/src/main/resources/"
    cp -fv application-${COMP}.yml application.yml ;
    cp -fv generator-${COMP}.properties generator.properties ;

    cd "${PROJ_ROOT}/renren-generator"
    mvn clean install && mvn spring-boot:run

    cd "${PROJ_ROOT}"
    ```

3. Start the generation

    ```bash
    PORT=80
    open "http://localhost:${PORT}#generator.html"
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

3. MySQL

    ```sql
    -- Just in case you wanna start over
    DROP DATABASE gulimall_pms_product   ;
    DROP DATABASE gulimall_oms_order     ;
    DROP DATABASE gulimall_sms_salepromo ;
    DROP DATABASE gulimall_ums_user      ;
    DROP DATABASE gulimall_wms_logistics ;
    ```
