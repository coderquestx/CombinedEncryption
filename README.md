# CombinedEncryption

**CombinedEncryption** is a hybrid encryption system that combines the strengths of both **ElGamal** and **DESX** encryption algorithms. It uses **ElGamal** for securely exchanging a symmetric key and **DESX** for fast and secure encryption of the actual data. This combination provides both the security of asymmetric encryption and the efficiency of symmetric encryption.

---

## Features

- **Hybrid Encryption**: Combines the security of **ElGamal** for key exchange with the speed of **DESX** for data encryption.
- **Asymmetric & Symmetric**: Uses **ElGamal** for securely exchanging the symmetric key, then encrypts the data using **DESX**.
- **Public-Key and Private-Key Security**: Leverages the advantages of both asymmetric and symmetric encryption.
- **Lightweight & Efficient**: Provides strong encryption while maintaining efficiency.
- **Java-Based**: Pure Java implementation with no external dependencies.

---

## Use Cases

- **Secure Data Transmission**: Encrypt large amounts of data efficiently by combining ElGamal for key exchange and DESX for fast encryption.
- **Encrypted Communication**: Ideal for applications where secure data transfer over public channels is required.
- **Hybrid Cryptosystems**: Use this implementation as a model for systems needing both asymmetric and symmetric encryption.

---

## Installation

Copy the `CombinedEncryption` class into your Java project. No additional libraries are required.

---

## Usage

### Basic Example

```java
public class Main {
    public static void main(String[] args) {
        String message = "Hello, Combined Encryption!";
        String desKey = "12345678"; 
        String keyXOR1 = "abcdefgh"; 
        String keyXOR2 = "ABCDEFGH"; 

        CombinedEncryption encryption = new CombinedEncryption(desKey, keyXOR1, keyXOR2);

        String encryptedMessage = encryption.encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = encryption.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
