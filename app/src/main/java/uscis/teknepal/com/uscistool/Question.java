package uscis.teknepal.com.uscistool;

/**
 * Created by TekNepal on 4/30/17. Free
 */

class Question {

        private String mQuestions [];

    {
        mQuestions = new String[]{
                        "If both the President and the Vice President can no longer serve, who becomes President?",
                        "If the President can no longer serve, who becomes President?",
                        "What is the Constitution?",
                        "What is an amendment?",
                        "What do we call the  1st ten amendments to the Constitution?",
                        "How many amendments does the Constitution have?",
                        "What is freedom of religion?",
                        "What is the economic system in the United States?",
                        "What is the “rule of law”?",
                        "Name one branch or part of the government.",
                        "What stops one branch of government from becoming too powerful?",
                        "Who is in charge of the executive branch?",
                        "Who makes federal laws?",
                        "What are the two parts of the U.S. Congress?",
                        "How many U.S. Senators are there?",
                        "We elect a U.S. Senator for how many years?",
                        "The House of Representatives has how many voting members?",
                        "We elect a U.S. Representative for how many years?",
                        "Who does a U.S. Senator represent?",
                        "We elect a President for how many years?",
                        "In what month do we vote for President?"
                };
    }

    private String mCorrectAnswers[] = {
                        "The Speaker of the House",
                        "The Vice President",
                        "The supreme law of the land",
                        "A change (to the Constitution)",
                        "The Bill of Rights",
                        "twenty-seven (27)",
                        "You can practice any religion, or not practice a religion.",
                        "capitalist economy",
                        "Everyone must follow the law.",
                        "President",
                        "checks and balances",
                        "the President",
                        "Congress",
                        "the Senate and House (of Representatives)",
                        "one hundred (100)",
                        "six (6)",
                        "four hundred thirty-five (435)",
                        "two (2)",
                        "all people of the state",
                        "four (4)",
                        "November"
                };


    private String mChoices [][] = {
                {
                        "The Speaker of the House",
                        "the Vice President",
                        "the President pro Tempore",
                        "the Secretary of state"
                },
                {
                        "the Secretary of state",
                        "The Vice President",
                        "the Speaker of the House",
                        "the President pro Tempore"
                },
                {
                        "The supreme law of the land",
                        "The highest court in the United States",
                        "A document declaring the United States an independent country",
                        "An agreement with England"
                },
                {
                        "A change (to the Constitution)",
                        "The highest court in the United States",
                        "the Bill of Rights",
                        "An agreement with England"
                },
                {
                        "Right to bear arms",
                        "the Bill of Rights",
                        "An agreement with England",
                        "All of the above"
                },
                {
                        "twenty-seven (27)",
                        "twenty-one (21)",
                        "twenty-one (23)",
                        "ten (10)"
                },
                {
                        "You must choose a religion",
                        "You can't choose the time you practice your religion",
                        "No one can practice a religion",
                        "You can practice any religion, or not practice a religion."
                },
                {
                        "central economy",
                        "capitalist economy",
                        "money economy",
                        "washingtion economy"
                },
                {
                        "Everyone must follow the Law.",
                        "Media needs to follow the Law",
                        "President does not follow the Law",
                        "you do not follow the Law"
                },
                {
                        "The Prime Minister",
                        "The vice President",
                        "The Speaker of the House",
                        "President"
                },
                {
                        "Federal Bureau of Investigation",
                        "the Cheif Justice",
                        "checks and balances",
                        "National Security Agency"
                },
                {
                        "the Cheif Justice",
                        "the President",
                        "the Speaker of the House",
                        "the Prime Minister"
                },
                {
                        "Donald J Trump",
                        "Federal Bureau of Investigation",
                        "Congress",
                        "the Supreme Court"
                },
                {
                        "the Senate and House (of Representatives)",
                        "Congress",
                        "the Supreme Court",
                        "Donald J Trump"
                },
                {
                        "one hundred (100)",
                        "two hundred (200)",
                        "three hundred (300)",
                        "four hundred (400)"
                },
                {
                        "eight (8)",
                        "six (6)",
                        "nine (9)",
                        "ten (10)"
                },
                {
                        "five hundred thirty-five (535)",
                        "four hundred thirty-five (435)",
                        "six hundred thirty-five (635)",
                        "nine hundred thirty-five (935)"
                },
                {
                        "six (6)",
                        "two (2)",
                        "five (5)",
                        "four(4)"
                },
                {
                        "all people of capital",
                        "all people of county",
                        "all people of the state",
                        "None of the above"
                },
                {
                        "four (4)",
                        "six (6)",
                        "nine (9)",
                        "ten (10)"
                },
                {
                        "Janaury",
                        "March",
                        "December",
                        "November"
                }
    };





    String getQuestion(int a) {
        return mQuestions[a];
        }


        String getChoice1(int a) {
            String choice0;
            choice0 = mChoices[a][0];
            return choice0;
        }


        String getChoice2(int a) {
            String choice1;
            choice1 = mChoices[a][1];
            return choice1;
        }

        String getChoice3(int a) {
            return mChoices[a][2];
        }

        String getChoice4(int a) {
        return mChoices[a][3];
    }

        String getCorrectAnswer(int a) {
            return mCorrectAnswers[a];
        }


}

