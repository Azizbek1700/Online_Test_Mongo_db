package uz.jl.configs;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import uz.jl.entity.Quiz;
import uz.jl.entity.auth.User;
import uz.jl.entity.question.Question;
import uz.jl.mappers.auth.UserMapper;
import uz.jl.mappers.question.QuestionMapper;
import uz.jl.mappers.quiz.QuizMapper;
import uz.jl.repository.auth.UserRepository;
import uz.jl.repository.question.QuestionRepository;
import uz.jl.repository.quiz.QuizRepository;
import uz.jl.security.SecurityHolder;
import uz.jl.service.auth.UserService;
import uz.jl.service.question.QuestionService;
import uz.jl.service.quiz.QuizService;
import uz.jl.ui.auth.AuthUserUI;
import uz.jl.ui.question.QuestionUI;
import uz.jl.ui.quiz.QuizUI;
import uz.jl.utils.BaseUtils;
import uz.jl.utils.validators.auth.UserValidator;
import uz.jl.utils.validators.question.QuestionValidator;
import uz.jl.utils.validators.quiz.QuizValidator;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ApplicationContextHolder {

    private static MongoDatabase db;
    private static MongoCollection<User> USER_MONGO_COLLECTION;
    private static MongoCollection<Question> QUESTION_MONGO_CONNECTION;
    private static final UserRepository USER_REPOSITORY;
    private static final QuestionRepository QUESTION_REPOSITORY;
    private static final UserMapper USER_MAPPER;
    private static final QuestionMapper QUESTION_MAPPER;
    private static final QuizMapper QUIZ_MAPPER;
    private static final UserValidator USER_VALIDATOR;
    private static final QuizValidator QUIZ_VALIDATOR;
    private static final QuestionValidator QUESTION_VALIDATOR;
    private static final UserService USER_SERVICE;
    private static final QuestionService QUESTION_SERVICE;
    private static final QuizService QUIZ_SERVICE;
    private static final AuthUserUI AUTH_USER_UI;
    private static final QuestionUI QUESTION_UI;
    private static final QuizUI QUIZ_UI;
    private static final BaseUtils BASE_UTILS;
    private static final SecurityHolder SECURITY_HOLDER;
    public static final QuizRepository QUIZ_REPOSITORY;

    static {
        connect();
        BASE_UTILS = new BaseUtils();
        USER_MAPPER = new UserMapper();
        QUESTION_MAPPER = new QuestionMapper();
        QUIZ_MAPPER = new QuizMapper();
        USER_VALIDATOR = new UserValidator();
        QUIZ_VALIDATOR = new QuizValidator();
        QUESTION_VALIDATOR = new QuestionValidator();
        USER_REPOSITORY = new UserRepository( User.class );
        QUESTION_REPOSITORY = new QuestionRepository( Question.class );
        USER_SERVICE = new UserService( UserRepository.class, UserMapper.class, UserValidator.class );
        QUESTION_SERVICE = new QuestionService( QuestionRepository.class, QuestionMapper.class, QuestionValidator.class );
        QUIZ_REPOSITORY = new QuizRepository( Quiz.class );
        QUIZ_SERVICE = new QuizService( QUIZ_REPOSITORY, QUIZ_MAPPER, QUIZ_VALIDATOR );
        AUTH_USER_UI = new AuthUserUI( USER_SERVICE );
        QUESTION_UI = new QuestionUI( QUESTION_SERVICE );
        QUIZ_UI = new QuizUI( QUIZ_SERVICE );
        SECURITY_HOLDER = new SecurityHolder();

    }

    public static <T> T getBean(Class<T> clazz) {
        return
                getBean( clazz.getSimpleName() );
    }

    private static <T> T getBean(String name) {
        name = name.toUpperCase( Locale.ROOT );
        return switch (name) {
            case "MONGODATABASE" -> (T) db;
            case "AUTHUSERUI" -> (T) AUTH_USER_UI;
            case "QUESTIONUI" -> (T) QUESTION_UI;
            case "QUIZUI" -> (T) QUIZ_UI;
            case "USERREPOSITORY" -> (T) USER_REPOSITORY;
            case "QUESTIONREPOSITORY" -> (T) QUESTION_REPOSITORY;
            case "QUIZREPOSITORY" -> (T) QUIZ_REPOSITORY;
            case "USERMAPPER" -> (T) USER_MAPPER;
            case "QUESTIONMAPPER" -> (T) QUESTION_MAPPER;
            case "QUIZMAPPER" -> (T) QUIZ_MAPPER;
            case "USERVALIDATOR" -> (T) USER_VALIDATOR;
            case "QUIZVALIDATOR" -> (T) QUIZ_VALIDATOR;
            case "QUESTIONVALIDATOR" -> (T) QUESTION_VALIDATOR;
            case "USERMONGOCOLLECTION" -> (T) USER_MONGO_COLLECTION;
            case "QUESTIONMONGOCONNECTION" -> (T) QUESTION_MONGO_CONNECTION;
            case "USERSERVICE" -> (T) USER_SERVICE;
            case "QUESTIONSERVICE" -> (T) QUESTION_SERVICE;
            case "QUIZSERVICE" -> (T) QUIZ_SERVICE;
            case "BASEUTILS" -> (T) BASE_UTILS;
            case "SECURITYHOLDER" -> (T) SECURITY_HOLDER;
            default -> throw new RuntimeException( "Bean Not Found Exception" );
        };
    }


    private static void connect() {
        ConnectionString connectionString = new ConnectionString( "mongodb://localhost:27017" );
        CodecRegistry pojoCodecRegistry = fromProviders( PojoCodecProvider.builder().automatic( true ).build() );
        CodecRegistry codecRegistry = fromRegistries( MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry );
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString( connectionString ).codecRegistry( codecRegistry ).build();

        Logger rootLogger = Logger.getLogger( "org.mongodb.driver" );
        rootLogger.setLevel( Level.OFF );
        try {
            MongoClient mongoClient = MongoClients.create( clientSettings );
            db = mongoClient.getDatabase( "online_contest" );
            USER_MONGO_COLLECTION = db.getCollection( "User", User.class );
            QUESTION_MONGO_CONNECTION = db.getCollection( "question", Question.class );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
