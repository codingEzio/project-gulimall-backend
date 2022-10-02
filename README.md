
## Prep

> macOS 

#### Tools

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

    > Inside our VM (CentOS/7)

    ```bash
    # 1. Remove existing docker just in case any problems arise
    # https://docs.docker.com/engine/install/centos/#uninstall-old-versions
    sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine


    # 2. Let yum know where to find Docker to install
    # https://docs.docker.com/engine/install/centos/#install-using-the-repository

    sudo yum install -y yum-utils
    sudo yum-config-manager \
        --add-repo \
        "https://download.docker.com/linux/centos/docker-ce.repo"

    sudo yum install docker-ce docker-ce-cli containerd.io docker-compose-plugin


    # 3. Configure mirrors Docker images for faster fetching
    # https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
    sudo mkdir -p /etc/docker
    sudo touch    /etc/docker/daemon.json
    sudo echo '{"registry-mirrors": ["https://22diassb.mirror.aliyuncs.com"]}' > /etc/docker/daemon.json


    # 4. Configure Docker to run when the OS boots
    sudo systemctl enable docker    # add to 'run when OS boots' list


    # 5. Run
    sudo systemctl start docker
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