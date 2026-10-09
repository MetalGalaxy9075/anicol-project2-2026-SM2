package au.edu.unimelb.swen.oop.mokepon;

public class AbilitySet {
    private Skill[] skills = new Skill[MokeponConstants.MAXSKILLS];

    public AbilitySet(Skill a, Skill b, Skill c, Skill d) {
        skills[0] = a;
        skills[1] = b;
        skills[2] = c;
        skills[3] = d;
    }

    public AbilitySet(String skillString) {
        passSkillString(skillString);
    }

    public void passSkillString(String skillString) {
        String[] individualSkills = skillString.split("\\|", MokeponConstants.MAXSKILLS);

        //Hard coded check to match player skill names to skill objects
        int i = 0;
        for (String skill : individualSkills) {
            if (skill.equals("Bite")) {
                skills[i] = new SkillBite();
            } else if (skill.equals("Tackle")) {
                skills[i] = new SkillTackle();
            } else if (skill.equals("Bunker")) {
                skills[i] = new SkillBunker();
            } else if (skill.equals("Wait")) {
                skills[i] = new SkillWait();
            } else if (skill.equals("Aim")) {
                skills[i] = new SkillAim();
            } else if (skill.equals("Swole")) {
                skills[i] = new SkillSwole();
            } else if (skill.equals("Nimble")) {
                skills[i] = new SkillNimble();
            } else if (skill.equals("Lance")) {
                skills[i] = new SkillLance();
            } else if (skill.equals("Stun")) {
                skills[i] = new SkillStun();
            } else if (skill.equals("Pocket Sand")) {
                skills[i] = new SkillPocketSand();
            }

            i = i + 1;
        }
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public int getNumbSkills() {
        int count = 0;
        for (Skill skill : skills) {
            if (skill != null)
                count++;
        }

        return count;
    }
}
