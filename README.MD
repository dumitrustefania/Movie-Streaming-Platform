# MOVIE STREAMING PLATFORM

322CA - Bianca Ștefania Dumitru
Object Oriented Programming Project, Stage #2

December 2022
----------------------------------------------------------------------------------------------------

* Movie Streaming Platform
    -  the program is an implementation of a movie streaming platform,
       such as Netflix, HBO Max or Disney+
    -  it stores a database with all the users registered on the platform,
       the movies that can be watched and details about them
    -  it reads input and prints output using the JSON format
    -  it is a file system tree-like structure: while being on a page,
       a user can move to another page or execute certain actions.

__Packages structure__

`main`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Main -> the main program where the test input files are opened and the
          output file is created and populated
       -> for every test, the Main class calls certain methods to initiate
          the new database and execute the required actions
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`fileio`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- SortInput -> defines the required sorting type (increasing or decreasing),
               depending on the rating or duration of a movie
- ContainsInput -> defines the required filters for movies: based on
                   the actors playing or the movie's genres
- FilterInput -> contains fields that define the required sorting
                 and filtering preferences
- ActionInput -> contains fields that define the characteristics of an
                 actionStrategy, such as type, feature and other specific fields
- Input -> main input class, used to extract the input data from every JSON file
        -> contains 3 arrays: users, movies and actions
- Output -> contains fields required in the output file
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`database`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Movie -> contains fields related to a specific movie

- Credentials -> contains the the credentials of an user

- User -> contains basic user information that has to be present in output

- Notification -> contains information about a notification: the movie name
                  and the message

- Database -> implemented as a Singleton using eager instantiation
           -> stores all the important information about the current state
              of the game: users, movies, current user, current movie(s),
              current page
           -> stores the stack of previous pages accessed, used for
              performing the 'back' operation
       
- Result -> implemented as a Singleton using eager instantiation
         -> contains an ArrayNode field the stores the output that
            will be transformed into JSON when all actions end
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
`pages`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Page -> an abstract class representing a page from on the platform
       -> contains 2 array fields (allowedActions and allowedNextPages)
          that are populated differently for each child class
       -> has the execute(ActionStrategy) method because a page represents
          the context for the Strategy pattern used for executing the actions
       -> has multiple child classes, each of them representing a different
          page: LoginPage, RegisterPage, MoviesPage, UpgradesPage, etc.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`action`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- ActionStrategy -> an abstract class representing an action that has
                    to be executed on a certain page
                 -> has the abstract method execute(), which is defined in
                    each of its child classes, that represent a Strategy
                    to be executed whenever a certain action is required
                 -> has multiple child classes, each of them representing
                    a different action: ChangePageActionStrategy,
                    PurchaseActionStrategy, RateActionStrategy, etc.
                    
- ActionStrategyFactory -> class used for the Factory design pattern
                        -> it can create all the ActionStrategy subclasses
                           based on the given input
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`observable`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- ObservableGenre -> class used for implementing the Observer design pattern
                  -> implemented using lazy singleton
                  -> contains a HashMap that stores the users subscribed to
                     each genre
                  -> a user is added as observer using the addObserver()
                     method when a subscribe action is performed
                  -> each user is notified using the notifyObservers() method
                     whenever a movie added to the database has one of the genres
                     he is subscribed to
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
----------------------------------------------------
How does it work?

* The Main class is the entry point of the program. It creates an
  Input instance using the input JSON file. It also initialises the
  database, then starts executing the actions one by one. Initially,
  the current page is the unauthenticated homepage.

* For each action, I first check whether it is a valid one.
  If everything is fine, I use the ActionStrategyFactory that
  know which subclass of the ActionStrategy has to be instantiated
  based on the input.

* After the new ActionStrategy subclass is instantiated, the current
  page calls its execute() method, so the required action
  gets executed.

* For each command that requires an output, an Output object is
  instantiated and the information about the current state of the
  platform is added to it. It then gets appended to the result ArrayNode.

* After all actions are done, the Main method transforms the result
  ArrayNode in JSON text and adds it to the resulting output file.

----------------------------------------------------

Design patterns

* used Singleton for the Database, Result and ObservableGenre, to have
  a unique instance for them and be able to get them wherever needed

* used Strategy to create strategies for executing each action

* used Factory to instantiate action Strategies based on the input

* used Observer to be able to easily notify all users subscribed to
  a genre whenever a movie containing that genre is added to the
  database