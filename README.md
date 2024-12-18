# Proiect-Etapa-1-POO

**Nume**: Păunescu Mihai-Ionuț  
**Grupa**: 322CD  
**Data**: 18.12.2024

## Descriere
J. POO Morgan Chase & Co. este un proiect în Java care modelează operațiunile  
bancare folosind design pattern-uri precum **Visitor** și **Factory**. Aplicația  
gestionează utilizatori, conturi, carduri, tranzacții și conversii valutare.

**Visitor** separă logica fiecărei comenzi și adaugă rezultatele în output, 
adaptat la fiecare tip de tranzacție.  
**Factory** creează și inițializează comenzile, returnând un obiect specific  
pentru fiecare tip de acțiune solicitată.

## Caracteristici

- **Gestionarea Utilizatorilor**: Adăugarea, ștergerea și afișarea detaliilor.
- **Administrarea Conturilor Bancare**: Crearea și ștergerea conturilor, setarea  
  soldului minim și modificarea dobânzii pentru conturi.
- **Managementul Cardurilor**: Crearea, ștergerea și verificarea cardurilor.
- **Tranzacții Financiare**: Trimiterea de bani, plăți online și împărțite între 
mai multe conturi.
- **Rapoarte Detaliate**: Generarea de rapoarte pentru tranzacții și cheltuieli.
- **Conversii Valutare**: Gestionarea conversiilor valutare internaționale.
- **Interfață JSON**: Generarea ieșirilor structurate în format JSON.

## Structura proiectului

### **Pachetul command**
Pachetul `command` implementează comenzile specifice aplicației. Comenzile sunt  
create folosind **CommandFactory**.

- **AddAccountCommand**: Adaugă un nou cont bancar.
- **AddFundsCommand**: Adaugă fonduri în contul specificat.
- **AddInterestCommand**: Aplică dobândă conturilor de economii.
- **ChangeInterestRateCommand**: Modifică rata dobânzii unui cont.
- **CheckCardStatusCommand**: Verifică starea unui card.
- **CreateCardCommand**: Creează un card nou pentru un cont.
- **DeleteAccountCommand**: Șterge un cont bancar.
- **DeleteCardCommand**: Șterge un card asociat unui cont.
- **NotImplementedCommand**: Placeholder pentru comenzi neimplementate.
- **PayOnlineCommand**: Realizează plăți online cu un card.
- **PrintTransactionsCommand**: Afișează istoricul tranzacțiilor.
- **PrintUsersCommand**: Afișează lista utilizatorilor din sistem.
- **ReportCommand**: Generează rapoarte detaliate ale tranzacțiilor.
- **SendMoneyCommand**: Transferă bani între conturi.
- **SetAliasCommand**: Setează un alias pentru un cont.
- **SetMinBalanceCommand**: Configurează soldul minim pentru conturi.
- **SpendingsReportCommand**: Generează rapoarte despre cheltuieli.
- **SplitPaymentCommand**: Realizează plăți împărțite între mai multe conturi.

### **Pachetul exception**
Pachetul `exception` conține excepțiile personalizate utilizate în sistem:

- **AccountCanNotBeDeletedException**: Contul nu poate fi șters.
- **AccountNotFoundException**: Contul specificat nu a fost găsit.
- **CardIsUsedException**: Cardul este deja utilizat într-o tranzacție.
- **CardNotFoundException**: Cardul specificat nu a fost găsit.
- **FrozenCardException**: Cardul este blocat pentru utilizare.
- **InsufficientFundsException**: Fonduri insuficiente pentru tranzacție.
- **NoExchangeRateException**: Rata de schimb necesară nu este disponibilă.
- **UnauthorizedAccessException**: Accesul utilizatorului este neautorizat.
- **UserAlreadyExistsException**: Utilizatorul există deja în sistem.
- **UserNotFoundException**: Utilizatorul specificat nu a fost găsit.

### **Pachetul model**
Pachetul `model` conține structurile principale de date din aplicație.

#### **Subpachetul account**
- **Account**: Clasa de bază pentru conturi.
- **ClassicAccount**: Conturi clasice cu funcționalități standard.
- **SavingsAccount**: Conturi de economii cu acumulare de dobândă.

#### **Subpachetul card**
- **Card**: Clasa de bază pentru carduri.
- **OneTimePayCard**: Carduri utilizabile o singură dată.
- **RegularCard**: Carduri obișnuite asociate conturilor.

#### **Subpachetul transaction**
- **AccountCreationTransaction**: Creează un cont nou.
- **AccountDeletionErrorTransaction**: Eroare la ștergerea unui cont.
- **CardCreationTransaction**: Creează un card pentru un cont.
- **CardDeletionTransaction**: Șterge un card din sistem.
- **CardPaymentTransaction**: Plată realizată folosind un card.
- **FrozenCardTransaction**: Blochează un card pentru utilizare.
- **InsufficientFundsTransaction**: Eroare de fonduri insuficiente.
- **InterestRateChangeTransaction**: Modifică dobânda unui cont.
- **MinimumAmountOfFundsTransaction**: Setează soldul minim necesar.
- **SendMoneyTransaction**: Transferă bani între conturi.
- **SplitPaymentTransaction**: Plată împărțită între conturi.

### **Pachetul service**
Pachetul `service` implementează logica principală a aplicației:

- **AccountService**: Administrează conturile bancare.
- **CardService**: Gestionează cardurile asociate conturilor.
- **ExchangeService**: Realizează conversii valutare.
- **TransactionService**: Gestionează tranzacțiile din sistem.
- **UserService**: Administrează utilizatorii și datele asociate acestora.

### **Pachetul visitor**
Pachetul `visitor` implementează **design pattern-ul Visitor**.

#### **Subpachetul command**
- **CommandVisitor**: Interfața pentru vizitarea comenzilor.
- **ConcreteCommandVisitor**: Implementarea logicii comenzilor.

#### **Subpachetul transaction**
- **TransactionVisitor**: Interfața pentru vizitarea tranzacțiilor.
- **ConcreteTransactionVisitor**: Implementarea logicii tranzacțiilor (crearea
outputului specific fiecărei tranzacții).  
