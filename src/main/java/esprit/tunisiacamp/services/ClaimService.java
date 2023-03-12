package esprit.tunisiacamp.services;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
<<<<<<< HEAD
=======
import edu.stanford.nlp.pipeline.CoreDocument;
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import esprit.tunisiacamp.entities.Claim;
import esprit.tunisiacamp.entities.User;
<<<<<<< HEAD
import esprit.tunisiacamp.entities.enums.Category;
=======
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.repositories.ClaimRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import javax.annotation.PostConstruct;
import java.util.*;
=======
import java.util.Date;
import java.util.List;
import java.util.Properties;
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
<<<<<<< HEAD
public class ClaimService implements IClaimsService {
=======
public class ClaimService implements IClaimsService{
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;
<<<<<<< HEAD
   /* public long adminId;
    @Bean
   public long ClaimProcessor(long adminId) {
         this.adminId = adminId;
        return adminId;
    }*/
=======
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df

    public void MyService(StanfordCoreNLP stanfordCoreNLP) {
        this.stanfordCoreNLP = stanfordCoreNLP;
    }
<<<<<<< HEAD

=======
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
    @Override
    public void addclaim(Claim c) {
        claimRepository.save(c);
    }

    @Override
<<<<<<< HEAD
    public void addclaimsandaffecterUser(Claim c, long idUser) {
        User user = userRepository.findById((int) idUser).get();//child
=======
    public void addclaimsandaffecterUser(Claim c,long idUser) {
        User user= userRepository.findById(idUser).get();//child
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
        //affecter child au parent
        c.setUser(user);
        c.setCreation(new Date());
        c.setState(false);
        claimRepository.save(c);
    }

    @Override
<<<<<<< HEAD
    public void modifyclaim(long idClaim, Claim c1) {
        Claim claim = claimRepository.findById(idClaim).get();
        claim.setTitle(c1.getTitle());
        // claim.setCategory(Category.OTHER);
        claim.setContent(c1.getContent());
        claim.setCreation(new Date());
        claim.setState(false);
        claimRepository.save(claim);
    }

    @Override
    public void modifyetatclaimsbyadmin(long idUser, long idClaim) {
        User adminUser = userRepository.findById((int) idUser).orElse(null);
=======
    public void modifyclaim(long idClaim,Claim c1) {
            Claim claim = claimRepository.findById(idClaim).get();
            claim.setTitle(c1.getTitle());
           // claim.setCategory(Category.OTHER);
            claim.setContent(c1.getContent());
            claim.setCreation(new Date());
            claim.setState(false);
            claimRepository.save(claim);
    }

    @Override
        public void modifyetatclaimsbyadmin(long idUser, long idClaim) {
        User adminUser = userRepository.findById(idUser).orElse(null);
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
        if (adminUser != null && adminUser.getRole().getRole() == role.ADMIN) {
            Claim claim = claimRepository.findById(idClaim).orElse(null);
            if (claim != null) {
                claim.setState(true);
                claim.setAdmin(adminUser);
                claimRepository.save(claim);
            }
        }
    }
<<<<<<< HEAD

=======
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
    @Override
    public void deleteclaim(long idClaim) {
        claimRepository.deleteById(idClaim);
    }

    @Override
    public List<Claim> retrieveclaimByUser(long idUser) {
<<<<<<< HEAD
        User user = userRepository.findById((int) idUser).get();
        return user.getMy_claims();
    }

    @Override
    public List<Claim> sortedComplaintsBySentiment(List<Claim> complaints) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        for (Claim complaint : complaints) {
            String text = complaint.getContent();
            int sentimentScore = calculateSentimentScore(pipeline, text);
            complaint.setSentimentScore(sentimentScore);
        }
        List<Claim> sortedComplaints = complaints.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getSentimentScore(), c1.getSentimentScore()))
                .collect(Collectors.toList());
        return sortedComplaints;

    }

    private int calculateSentimentScore(StanfordCoreNLP pipeline, String text) {
        // List<Claim> complaint= (List<Claim>) claimRepository.findAll();
=======
            User user=userRepository.findById(idUser).get();
            return user.getMy_claims();
    }

    @Override
        public List<Claim> sortedComplaintsBySentiment(List<Claim> complaints) {
            Properties props = new Properties();
            props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
            for (Claim complaint : complaints) {
                String text = complaint.getContent();
                int sentimentScore = calculateSentimentScore(pipeline, text);
                complaint.setSentimentScore(sentimentScore);
            }
            List<Claim> sortedComplaints = complaints.stream()
                    .sorted((c1, c2) -> Integer.compare(c2.getSentimentScore(), c1.getSentimentScore()))
                    .collect(Collectors.toList());
            return sortedComplaints;

    }
    private int calculateSentimentScore(StanfordCoreNLP pipeline, String text) {
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
        int sentimentScore = 0;
        if (text != null && text.length() > 0) {
            Annotation annotation = pipeline.process(text);
            List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                switch (sentiment) {
                    case "Very negative":
                        sentimentScore += -2;
                        break;
                    case "Negative":
                        sentimentScore += -1;
                        break;
                    case "Neutral":
                        sentimentScore += 0;
                        break;
                    case "Positive":
                        sentimentScore += 1;
                        break;
                    case "Very positive":
                        sentimentScore += 2;
                        break;
                }
            }
        }
        return sentimentScore;
    }

    @Override
    public List<Claim> getAllClaims() {
<<<<<<< HEAD

        return (List<Claim>) claimRepository.findAll();
    }

    @Override
    public List<Claim> getUnresolvedClaims() {
        return claimRepository.findByState(false);
    }

    @Override
    public void processUnresolvedClaims(long adminId) {
        // User adminUser = claimRepository.findByRole(Role.class).orElse(null);
        List<Claim> unresolvedClaims = getUnresolvedClaims();
        for (Claim claim : unresolvedClaims) {
            int sentimentScore = calculateSentimentScore(stanfordCoreNLP, claim.getContent());
            // List<User> adminUser=userRepository.findAll();
            if (sentimentScore < 0) {
                modifyetatclaimsbyadmin(adminId, claim.getIdClaim());
            } else {
                markAsResolved(claim);
            }
        }
    }

    private void markAsResolved(Claim claim) {
        claim.setState(true);
        claimRepository.save(claim);

    }

    @Override
   public List<Object[]> countClaimsByCategory(){
        return claimRepository.countByCategory();
    }
    @Override
    public Map<String, Long> getClaimsByCategory() {
        List<Object[]> results = claimRepository.countByCategory();
        Map<String, Long> counts = new HashMap<>();
        for (Object[] result : results) {
            Category category = (Category) result[0];
            Long count = (Long) result[1];
            counts.put(category.name(), count);
        }
        return counts;
    }

   /* @Override
    public List<Object[]> countByCategoryBetweenDates(Date startDate, Date endDate) {
        return null;
    }*/

    @Override
    public Map<Category, Long> countClaimsByCategoryBetweenDates(Date startDate, Date endDate) {
        List<Object[]> results = claimRepository.countClaimsByCategoryBetweenDates(startDate, endDate);
        Map<Category, Long> countByCategory = new HashMap<>();
        for (Object[] result : results) {
            Category category = (Category) result[0];
            Long count = (Long) result[1];
            countByCategory.put(category, count);
        }
        return countByCategory;
    }


}
=======
        return (List<Claim>) claimRepository.findAll();
    }

}
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
