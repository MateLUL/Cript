# Cript
Cript is an easy-to-use and versatile encryption and decryption tool, currently in development, written in Java.

## Building
To use the program, you must build it first using `javac`.

`javac Cript.java`

## Usage
The usage is simple.

`java Cript <Encryption method> <Process> <Source> <Content>`

- **Encryption method**: there is currently 1 method available, `binary`.

- **Process**: there is currently 1 process available, `encode`.

- **Source**: you can choose whether you want to use a .txt file (using `file`), or pass through your text directly (using `text`).

- **Content**: if you selected to use a file, you also have to choose which file you would like to use. You can either use an *absolute* or *canonical* path. But, if you selected text, you can type your message here directly, with quotation marks.


### For example:

`java Cript binary encode file myFile.txt` 

will produce a new .txt file, with the binary encryption of *myFile.txt*'s content, with the output of:

*The encrypted text was saved in /home/user/myFileEncrypted.txt*


`java Cript binary encode text "This is the text I want to encrypt"`

will have an output of:

*The encrypted text: 01010100 01101000 01101001 01110011 00100000 01101001 01110011 00100000 01110100 01101000 01100101 00100000 01110100 01100101 01111000 01110100 00100000 01001001 00100000 01110111 01100001 01101110 01110100 00100000 01110100 01101111 00100000 01100101 01101110 01100011 01110010 01111001 01110000 01110100*
