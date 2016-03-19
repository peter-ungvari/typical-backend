Virtual Machine server setup
============================

1. Download Ubuntu 14.04 LTS (Supported till 2019)

  http://www.ubuntu.com/download/server/

2. Create a new virtual machine using VirtualBox

  Default settings for Ubuntu x64.

3. Install Ubuntu to VM

  Username: ubuntu
  Password: ubuntu
  Packages: Open SSH server, PostgreSQL server

4. Optional: Set up VirtualBox integration services for guest OS

5. Set up port forwarding for SSH on VirtualBox VM

  On VirtualBox GUI: Network Settings > Port Forwarding,
  name: ssh,
  protocol: tcp,
  Host port: 2022,
  Guest port: 22

6. Optional: set up sudo without password

  Open sudoers config file for editing (will open in nano editor by default):

        sudo visudo

  Add this line to the bottom:

        username ALL=(ALL) NOPASSWD: ALL

  Save the file with `Ctrl+X Y`