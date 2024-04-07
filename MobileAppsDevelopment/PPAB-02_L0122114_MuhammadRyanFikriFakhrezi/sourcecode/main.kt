// Nama : Muhammad Ryan Fikri Fakhrezi
// NIM  : L0122114
// Kelas: C

//abstract class
abstract class EncryptorDecryptor {
    abstract fun encrypt(text:String):String
    abstract fun decrypt(text:String):String
}

//inheritance
class caesarCipher(key: Int = 23): EncryptorDecryptor(){

    //collection (Map)
    private val alphabet_encrypt = mutableMapOf<Char, Char>()
    private val number_encrypt = mutableMapOf<Int, Int>()
    private val alphabet_decrypt = mutableMapOf<Char, Char>()
    private val number_decrypt = mutableMapOf<Int, Int>()

    init{
        //lambda expressions
        (65..90).forEach { num ->
            alphabet_encrypt[num.toChar()] = (((num % 65) + key) % 26 + 65).toChar()
            alphabet_decrypt[num.toChar()] = (((num % 65) - key + 26) % 26 + 65).toChar()
        }
        
        (48..57).forEach { num ->
            number_encrypt[num % 48] = (((num % 48) + key) % 10 + 48) % 48
            number_decrypt[num % 48] = (((num % 48) - key + 10) % 10 + 48) % 48
        }
    }

    override fun encrypt(text:String):String{
        var encryptedString : String = ""

        // Control flow (for loop)
        for(letter in text){    
            if(letter.isUpperCase()){
                encryptedString += alphabet_encrypt[letter]
            }else if(letter.isLowerCase()){ 
                encryptedString += alphabet_encrypt[letter.uppercaseChar()]?.lowercaseChar()
            }else if(letter.isDigit()){
                encryptedString += number_encrypt[letter.digitToInt()]
            }else{
                encryptedString += letter
            }
        }
    
        return encryptedString
    }

    override fun decrypt(text:String):String{
        var decryptedString : String = ""

        // Control flow (for loop)
        for(letter in text){    
            if(letter.isUpperCase()){
                decryptedString += alphabet_decrypt[letter]
            }else if(letter.isLowerCase()){ 
                decryptedString += alphabet_decrypt[letter.uppercaseChar()]?.lowercaseChar()
            }else if(letter.isDigit()){
                decryptedString += number_decrypt[letter.digitToInt()]
            }else{
                decryptedString += letter
            }
        }
    
        return decryptedString
    }
}

fun main(){
    println("THIS IS SIMPLE TEXT ENCRYPTOR")
    var key : Int?
    do{
        print("Enter the encryption key : ") 
        key = readLine()!!.toIntOrNull()
        if(key == null) println("Not a valid number, try again")
    }while(key == null) // Control flow (do-while loop)

    var encryptor : caesarCipher = caesarCipher(key)

    var choice : Int?
    while(true){
        println("\n1. Encrypt\n2. Decrypt\n3. Quit")
        do{
            print("What do you want to do : ")
            choice = readLine()!!.toIntOrNull()
            if(choice == null) println("Not a valid number, try again")
        }while(choice==null) // Control flow (do-while loop)

        //Control flow (when wxpression)
        when(choice){
            1 -> {
                print("Enter the text to be encrypted : ")
                var encryptedText = encryptor.encrypt(readLine()!!)
                println("Encrypted text : ${encryptedText}")
            }
            2 -> {
                print("Enter the text to be decrypted : ")
                var decryptedText = encryptor.decrypt(readLine()!!)
                println("Decrypted text : ${decryptedText}")
            }
            3 -> break
        }
    }
}