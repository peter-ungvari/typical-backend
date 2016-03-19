Virtual Machine Server Setup
============================

1. Download Ubuntu 14.04 LTS (Supported till 2019)
  - http://www.ubuntu.com/download/server/

2. Create a new virtual machine using VirtualBox
  -Default settings for Ubuntu x64.

3. Install Ubuntu to VM
  - Username: ubuntu
  - Password: ubuntu
  - Packages: Open SSH server, PostgreSQL server

4. Optional: Set up VirtualBox integration services for guest OS

5. Set up port forwarding for SSH on VirtualBox VM
  - This way the server can be used through an ssh client and files can be copied with scp to the server.
  - On VirtualBox GUI: Network Settings > Port Forwarding
    - name: `ssh`
    - protocol: `tcp`
    - Host port: `2022`
    - Guest port: `22`

6. Optional: set up sudo without password
  - Open sudoers config file for editing (will open in nano editor by default):

          sudo visudo

  - Add this line to the bottom:

          username ALL=(ALL) NOPASSWD: ALL

  - Save the file with `Ctrl+X Y`

7. Install Oracle Java

        sudo apt-add-repository ppa:webupd8team/java
        sudo apt-get update
        # sudo apt-get install oracle-java8-installer
        sudo apt-get install oracle-java8-set-default

8. Create DB user and database

        # add system user with no home directory, required for default postgresql user mapping
        sudo useradd -r -s /bin/false typical-backend-spring 

        # create DB user
        # this will ask for password
        # enter typical-backend-spring also as password
        sudo -u postgres createuser -D -A -P typical-backend-spring
        
        #create empty database for the DB user
        sudo -u postgres createdb -O typical-backend-spring typical-backend-spring

  More information here: https://help.ubuntu.com/community/PostgreSQL

9. Use DB client to ensure the DB is set up correctly.

        sudo -u typical-backend-spring psql typical-backend-spring -c "\list"

        # alternatively, in interactive mode
        sudo -u typical-backend-spring psql typical-backend-spring
        \list
        \dt
        \q

  - `\q`: Quit from the interactive shell.
  - `\dt`: List the tables of the current database. With `*.*` argument all tables will be listed.
  - `\list`: List databases, without tables.
  - `\?`: Show help.