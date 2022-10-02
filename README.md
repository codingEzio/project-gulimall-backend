
## Prep

> macOS 

#### Tools

1. Vagrant

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