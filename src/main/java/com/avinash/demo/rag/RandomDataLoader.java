package com.avinash.demo.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RandomDataLoader {

    private final VectorStore vectorStore;

    public RandomDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadSentenceIntoVectorStore() {
        List<String> sentences = List.of(
                "Python simplifies data analysis with readable syntax.",
                "Saving money requires discipline and consistent budgeting habits.",
                "Books expand imagination and improve vocabulary over time.",
                "Java is widely used for enterprise-level applications.",
                "Investing money early builds long-term financial stability.",
                "Reading books daily enhances critical thinking skills.",
                "JavaScript powers interactive features on modern websites.",
                "Managing money wisely prevents unnecessary debt.",
                "Fiction books transport readers into different worlds.",
                "C++ offers control over system-level programming tasks.",
                "Money management apps help track daily expenses.",
                "Books provide valuable insights into human behavior.",
                "Rust ensures memory safety without garbage collection.",
                "Saving money regularly builds a secure future.",
                "Books can inspire creativity and innovation.",
                "Go language excels in concurrent programming environments.",
                "Money invested in education yields lifelong returns.",
                "Books help develop empathy and understanding.",
                "Swift is popular for iOS application development.",
                "Budgeting money helps achieve financial goals faster.",
                "Books offer knowledge across diverse subjects.",
                "Kotlin is preferred for modern Android development.",
                "Smart money decisions reduce financial stress.",
                "Books can be both educational and entertaining.",
                "TypeScript improves JavaScript with static typing.",
                "Earning money online has become increasingly common.",
                "Books shape perspectives and broaden horizons.",
                "PHP is widely used for server-side scripting.",
                "Money saved today supports future emergencies.",
                "Books provide timeless wisdom and insights.",
                "Ruby emphasizes simplicity and productivity in coding.",
                "Spending money wisely avoids unnecessary financial burden.",
                "Books enhance language and communication skills.",
                "SQL manages structured data efficiently in databases.",
                "Money planning ensures financial independence.",
                "Books help in gaining specialized knowledge.",
                "Bash scripting automates repetitive system tasks.",
                "Money habits determine long-term wealth accumulation.",
                "Books encourage lifelong learning habits.",
                "Scala combines functional and object-oriented programming.",
                "Tracking money prevents overspending issues.",
                "Books are essential tools for education.",
                "Dart is used in Flutter for mobile apps.",
                "Saving money helps achieve personal dreams.",
                "Books improve concentration and mental focus.",
                "MATLAB is used for numerical computing tasks.",
                "Money invested wisely grows exponentially.",
                "Books can teach valuable life lessons.",
                "Perl excels in text processing tasks.",
                "Managing money reduces financial anxiety.",
                "Books provide access to expert knowledge.",
                "Assembly language offers low-level hardware control.",
                "Money goals guide financial decision making.",
                "Books support continuous personal development.",
                "Haskell focuses on pure functional programming.",
                "Money discipline builds financial security.",
                "Books inspire innovation and new ideas.",
                "COBOL is still used in legacy systems.",
                "Money saved can be invested for growth.",
                "Books provide historical and cultural insights.",
                "Julia is efficient for scientific computing.",
                "Budgeting money ensures balanced spending habits.",
                "Books can improve writing abilities.",
                "Groovy integrates well with Java ecosystems.",
                "Money mistakes can teach valuable lessons.",
                "Books enhance knowledge retention and memory.",
                "Elixir is designed for scalable applications.",
                "Saving money regularly builds confidence.",
                "Books can change perspectives dramatically.",
                "F# supports functional programming in .NET.",
                "Money management requires careful planning.",
                "Books are valuable learning resources.",
                "Objective-C was used before Swift in iOS.",
                "Money invested early benefits from compounding.",
                "Books help improve analytical thinking skills.",
                "Crystal language focuses on performance and simplicity.",
                "Money tracking apps simplify budgeting.",
                "Books encourage curiosity and exploration.",
                "Zig offers control and safety in programming.",
                "Money habits influence financial future.",
                "Books are essential for academic success.",
                "Smalltalk introduced object-oriented programming concepts.",
                "Money saved helps during emergencies.",
                "Books improve comprehension and reasoning skills.",
                "Nim combines performance with readable syntax.",
                "Money management tools help track investments.",
                "Books develop imagination and creativity.",
                "Fortran is used in scientific computations.",
                "Money saved reduces financial pressure.",
                "Books provide endless learning opportunities.",
                "Apex is used on Salesforce platform.",
                "Money planning helps achieve life goals.",
                "Books enhance knowledge across disciplines.",
                "OCaml supports functional and imperative programming.",
                "Money awareness prevents impulsive spending.",
                "Books contribute to intellectual growth.",
                "Chapel is designed for parallel computing.",
                "Money invested grows with time.",
                "Books offer guidance and inspiration.",
                "Programming languages evolve with technological advancements."
        );
        List<Document> documentList = sentences.stream().map(Document::new).toList();
        vectorStore.add(documentList);
    }
}
