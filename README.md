
## Prep

> macOS 

#### Tools

1. Vagrant

    - Get it working

    ```bash
    brew install vagrant    # use the GUI way if you prefer

    vagrant --version       # check if installed correctly

    vagrant init centos/7   # config file
    vagrant up              # download, config and boot
    vagrant ssh             # ssh into the virtual machine

    - Commonly used commands

    ```bash
    # cd the where the Vagrantfile file lies in
    vagrant status
    vagrant suspend     # sleep
    vagrant up          # boot

    vagrant halt        # shutdown
    vagrant reload      # reboot
    ```

#### Software
