'''
   Author: Levi Hutchins
   This is the main program behind the whole project. It calls other reliant scripts.
   In summary, when this file is executed with the correct arguments, it will convert either
   the text file or text file to binary and then will be encrypted by the AES file.
   After this the C++ test file will be executed to determine whether the decrypted file
   is identical to the original. C++ was chosen for it speed.
'''

# Import all required libaries
import sys, getopt
import base64
import os
import subprocess

'''
   * @brief Convert image to binary
   * @param an img to convert and output location
   * @return None
'''
def image_to_binary(image,out):
   
   try:
      # Open the image in read binary mode
      with open(image, "rb") as f: encode = base64.b64encode(f.read())
      # In order to get the binary in the format I needed I had to reformat 
      binary = "".join([format(n, '08b') for n in encode])
      file = open(out, "w")
      file.write(binary)
      file.close()
   except IOError:
      print("An error occurred")

   print("Image Successfully Converted.")

'''
   * @brief Convert text file to binary
   * @param a file to convert and output location
   * @return None
'''
def txtFile_to_binary(txtFile,out):

   try:
      file = open(txtFile,'r')
      text = file.read()
      binaryText = str(''.join(format(ord(i), '08b') for i in text))
      with open(out, 'w') as writeMe: writeMe.write(binaryText)
   except IOError:
      print("An error occurred")

   print("Text Successfully Converted.")

'''
   * @brief determine file extension
   * @param input file and output location/file
   * @return None
'''
def determineFile(inputfile,outputfile):
   if inputfile.partition(".")[2] == "jpg":
      image_to_binary(inputfile,outputfile)
   else:
      txtFile_to_binary(inputfile,outputfile)

'''
   * @brief Call other dependent scripts
   * @param None
   * @return None
'''
def callOtherScripts():
   os.chdir('AES')
   subprocess.run(['javac', 'main.java'])
   subprocess.run(['java', 'main'])
   os.chdir('../test')
   subprocess.run(['make'])
   subprocess.run(['./runme'])



'''
   * @brief main program runs entire system
   * @param arguments from command line
   * @return None
'''
def main(argv):
   inputfile = ''
   outputfile = ''
   try:
      opts, args = getopt.getopt(argv,"hi:o:",["ifile=","ofile="])
   except getopt.GetoptError:
      print ('input_image.py -i <inputfile> -o <outputfile>')
      sys.exit(2)
   for opt, arg in opts:
      if opt == '-h':
         print ('input_image.py -i <inputfile> -o <outputfile>')
         sys.exit()
      elif opt in ("-i", "--ifile"):
         inputfile = arg
      elif opt in ("-o", "--ofile"):
         outputfile = arg
   if inputfile != '' and outputfile != '': determineFile(inputfile,outputfile)
   else:
      print("To use this program simply type: "
            ""+'\033[1m' + "python main.py -i 'inputfile' -o 'outputfile'" + '\033[0m')



if __name__ == "__main__":
   main(sys.argv[1:])
   callOtherScripts()

