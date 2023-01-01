# AES Encryption System
### Description
This was my first real project I designed and implemented all on my own. I wanted to do an encryption system due to my cyber security passion. This system allows for encryption and decryption of images as well as text files. It uses AES ( Advanced Encryption Standard) 128 bit as the form of encryption so it is reasonably strong but mainly this program is lightweight. It also includes a C++ script to evaluate the decrypted file and orginial binary file to verifys they are the same. 

### How Does it Work?
- After the command is given the input file is passed into a function to determine if it is an image or text file. Once then extension is determined the file is then converted to its binary form.
- Next, the binary file is written to a new text file in the ouput folder. Once that is complete the python script calls the main.java file which performs the encryption and decryption of the original binary file.
- Finally, once that is finished the test.cpp file is executed which compares the decrypted file and original file to ensure they are identical.
- Note: This program will need to be run inside some sort of Linux state due to the makefile. I developed this in Ubuntu however i can recommend Cygwin terminal for windows users. 

###
<details><summary>How to Use</summary>
<p>

#### Command

```
python main.py -i "input file" -o "output file"
```

</p>
</details>

<details><summary>Sample Output</summary>
<p>

#### Command

```
python main.py -i images/image1.jpg -o output/new.txt
Image Successfully Converted.
Encrypted Successfully
Decrypted Successfully
make: 'all' is up to date.
Files Match!
Time taken to compare file: 
--- 4258 Microseconds ---

  
```

</p>
</details>

### NOTE: If you are going to use this program be sure to change the paths / files in some of the programs. The Encrypted and Decrypted files in the output folder are of the image1.jpg located inside the image folder.


