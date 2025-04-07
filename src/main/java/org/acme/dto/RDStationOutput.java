package org.acme.dto;

import java.util.List;

public class RDStationOutput {

    private boolean hasMore;
    private List<Organization> organizations;
    private int total;

    // Construtor padrão
    public RDStationOutput() {
    }

    // Getters and Setters
    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class Organization {
        private String id;
        private String address;
        private List<Contact> contacts;
        private String createdAt;
        private List<String> customFields;
        private int lostCount;
        private String name;
        private int openedCount;
        private List<String> organizationSegments;
        private int pausedCount;
        private String resume;
        private String updatedAt;
        private String url;
        private User user;
        private int wonCount;

        // Construtor padrão
        public Organization() {
        }

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<Contact> getContacts() {
            return contacts;
        }

        public void setContacts(List<Contact> contacts) {
            this.contacts = contacts;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public List<String> getCustomFields() {
            return customFields;
        }

        public void setCustomFields(List<String> customFields) {
            this.customFields = customFields;
        }

        public int getLostCount() {
            return lostCount;
        }

        public void setLostCount(int lostCount) {
            this.lostCount = lostCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOpenedCount() {
            return openedCount;
        }

        public void setOpenedCount(int openedCount) {
            this.openedCount = openedCount;
        }

        public List<String> getOrganizationSegments() {
            return organizationSegments;
        }

        public void setOrganizationSegments(List<String> organizationSegments) {
            this.organizationSegments = organizationSegments;
        }

        public int getPausedCount() {
            return pausedCount;
        }

        public void setPausedCount(int pausedCount) {
            this.pausedCount = pausedCount;
        }

        public String getResume() {
            return resume;
        }

        public void setResume(String resume) {
            this.resume = resume;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int getWonCount() {
            return wonCount;
        }

        public void setWonCount(int wonCount) {
            this.wonCount = wonCount;
        }

        public static class Contact {
            private String birthday;
            private List<Email> emails;
            private String facebook;
            private String linkedin;
            private String name;
            private String notes;
            private List<Phone> phones;
            private String skype;
            private String title;

            // Construtor padrão
            public Contact() {
            }

            // Getters and Setters
            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public List<Email> getEmails() {
                return emails;
            }

            public void setEmails(List<Email> emails) {
                this.emails = emails;
            }

            public String getFacebook() {
                return facebook;
            }

            public void setFacebook(String facebook) {
                this.facebook = facebook;
            }

            public String getLinkedin() {
                return linkedin;
            }

            public void setLinkedin(String linkedin) {
                this.linkedin = linkedin;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNotes() {
                return notes;
            }

            public void setNotes(String notes) {
                this.notes = notes;
            }

            public List<Phone> getPhones() {
                return phones;
            }

            public void setPhones(List<Phone> phones) {
                this.phones = phones;
            }

            public String getSkype() {
                return skype;
            }

            public void setSkype(String skype) {
                this.skype = skype;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public static class Email {
                private String email;

                // Construtor padrão
                public Email() {
                }

                // Getter and Setter
                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }
            }

            public static class Phone {
                private String phone;
                private String type;

                // Construtor padrão
                public Phone() {
                }

                // Getters and Setters
                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }

        public static class User {
            private String id;
            private String email;
            private String name;
            private String nickname;

            // Construtor padrão
            public User() {
            }

            // Getters and Setters
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}



