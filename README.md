# Cript
Cript is an easy-to-use and versatile encryption/decryption and encoding/decoding tool, currently in development, written in Java.

## Building
To use the program, you must build it first using `javac`.

`javac Cript.java`

## Usage
The usage is simple.

`java Cript <Method> <Process> <Source> <Content>`

- **Method**: there is currently 1 method available, `binary`.

- **Process**: there is currently 1 method that supports both encoding (`encode`) and decoding (`decode`), which is binary.

- **Source**: you can choose whether you want to use a .txt file (using `file`), or pass through your text directly (using `text`).

- **Content**: if you selected to use a file, you can choose which file you would like to use. But, if you selected text, you can type your message here directly, with quotation marks.


### For example:

`java Cript binary encode file myFile.txt` 

will produce a new .txt file, with the binary encoding of *myFile.txt*'s content, with the output of:

>The encoded text was saved in /home/user/myFileEncoded.txt


`java Cript binary encode text "Hello World"`

will have an output of:

>The encoded text: 01001000 01100101 01101100 01101100 01101111 00100000 01010111 01101111 01110010 01101100 01100100
