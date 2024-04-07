// Nama : Muhammad Ryan Fikri Fakhrezi
// NIM  : L0122114
// Kelas: C

// Function
fun encrypt(string:String, key:Int = 23 /* default argument */):String{
    var encryptedString : String = "" // Data types & Variables
    var dynamicBorder : String = ""
    for(letter in string){
        var ascii_before : Int = letter.code
        var ascii_after : Int? = null // Nullable types

        // If expressions
        if(letter.isUpperCase()){
            ascii_after = ((ascii_before % 65) + key) % 26 + 65
        }else if(letter.isLowerCase()){
            ascii_after = ((ascii_before % 97) + key) % 26 + 97
        }else if(letter.isDigit()){
            ascii_after = ((ascii_before % 48) + key) % 10 + 48
        }

        dynamicBorder += if (letter.equals('\t')) "========" else "="
        // Safe calls & elvis operator
        encryptedString += ascii_after?.toChar() ?: ascii_before.toChar()
    }

    // Raw String & String Template
    return """
    =======================${dynamicBorder}====
        unencrypted text : ${string}
        encrypted text   : ${encryptedString}
        key              : ${key}
    =======================${dynamicBorder}====
    """
}

fun main(){
    println(encrypt(string = "Ryan Fikri\tL0122114" /* escaped string */, key = 3)) // named argument
}