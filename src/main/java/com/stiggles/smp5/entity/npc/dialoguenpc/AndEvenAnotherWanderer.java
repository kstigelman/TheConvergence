package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AndEvenAnotherWanderer extends StigglesNPC {
    String signature = "M6BeWuJEmBW988X0Fn3cg6jh3k2ctc6FB76z0bPvJ9aFfit2hEfSlhiO/traMHSh1dMuli5L7ZKS0xg/Uq24phte7IscKmS7NUCFt2wudmq997Sw33v8pH0L/25GbxXVJsxbLuF98HT310cGfgtMGLK8J2XZKTrczkB+XPIkRhoRl+PQfenXf9X2ZaEFu7JVGdJcEGIxK9VY1eF1Glx04DUsLVZ8v4G3qP162CUj7XrxRTKoapJ6nx2nVoNUJ+BEAN/vWmxB4kIrUT6UoXw/wUaCvoCoGruddxGChL+399c+iodUz6NpsADE9+7lgnbDjG7GDBl7uB6zEP302NfXhVg7owCv0vgz0l3Rp25CGy5MZf/REtgkl7+HEiaJRW1tEJWENUuIsWzk84hUaD9aFtI7UTy7v4H7HBon2UhOH1S0ID3YLAfaERIWn4wyK0stI1Ie/rwjuRV1AzWbXksu+h0b5ounsBQc81uJntkkXQXFGx2abY7Tk83u374OvTrOt8cJSZBE2rWjlzGKUloDpS8dQ2is1ZjDYkVWPJQMu3dEnXfFatSvo3wyWnyIwnjJ9DPDNgivq5okOhAslX4Q+0XIJDjiwq9wMnI33WPCDx5oO/ob8F7uKtedL633BVwl5QtAo9W5Ykhpq1nsMnYrYO9xF6r/twg4Y5i3FxIOP08=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4Njk2MjI0ODUxNiwKICAicHJvZmlsZUlkIiA6ICJlYjA3ZmQzMmFiOTE0NjRjODVjYmU1YjVhYTlkYTRjZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJ4bUUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGM4YjZjMGNjMGJkNTM1OWQ4NGRlMGRiMjFhNDRlMDAwOTFkYjViNTcyZDllN2M0MjQ3NDQ5M2E3MTQ5NzgxZCIKICAgIH0KICB9Cn0=";

    public AndEvenAnotherWanderer(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(value, signature);
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "I just heard there would be an adventure. Other than that, I don't know why I'm here.");
        speakLater(player, "I just wish we brought some snacks.", Sound.ENTITY_VILLAGER_NO, 80);
    }

}
