package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Ralph extends ShopNPC {
    public Ralph (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4NTk4NDEyNDYxMSwKICAicHJvZmlsZUlkIiA6ICJhYWQzYmVlNGNhYzI0ZGQyODJlZTQxOWRjNzQ1ZmE0MiIsCiAgInByb2ZpbGVOYW1lIiA6ICJPc2hpXzAzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNlMjk2YjNjZWM0ZmY0MmNkMGU2OTQxZjA4NWUwMTcxZWYxYWY5NjI2MzE1MDMyNzU0Y2M2YjUzZTE0ZWZmYzAiCiAgICB9CiAgfQp9",
                "ageu9DbJhKhSHXDXG1HHXeLaukMF6tu5H5+IHj6kBYxveuOP3retZnYwvt2S1l6DyNNdZoDRs7Oy/L7Ypy+wvoCIzZDnNfCBADXUtjUXVdCBgIJyTebNkY+bCKcg1CaGWS78foBHL1h7iRHAWOlmws7dIIiW1pJB2oNkJDcWLk25wNnIKYyJBQNLgwE63zQYqSyjPD3ruwq8rWFFRuAJe7AbwKChI/0m/91GitoZfC1nIhjTW6XbW1QS8TQOxdJ81qrgJIOgXI2q4F8kdh5XaY+jsrkcv0WZ5bwNUYv0Upg7HdVVRUvG8ZsX/u5Ix/euarx5L96z+rUcbK92a7mAwL1+/aAWh3LKvQiNPmttPa2DNsXeBS27F+SI696h2OMud80VvmYTyK63ZS8yaB1RNiprnNWzYEfS9pJJLkFvxdlitlpOdDFPchFsPqFv8uV61MC5zTg/XDTaPVXlGsG8hKZxEu32LL/6bePyHQkrBFlY6GcYW+4GrnHnHVISwIoStHF51FOV+lRKUQHcD3DkmiDmPCCyh6LIzbbC2IdEY7uyN6QY51yAhIdKsa40l/Wf26CrfcX0/QQRvlDhQ2VltGMpVKMzm4rOhjoyhpSnFkxoiv9uZWAhl4oiRwSy+UV2dt0Ibj5MsJdRH8XxGvwAN1N58QwPonFGzySHMq2GkW8="
        );
    }
    @Override
    public void createGUI(Player player) {

    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Hello. What can we help ya build?");
    }
}
