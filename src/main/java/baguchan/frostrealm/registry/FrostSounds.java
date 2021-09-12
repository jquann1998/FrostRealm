package baguchan.frostrealm.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FrostSounds {
  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "frostrealm");
  
  public static final RegistryObject<SoundEvent> FRSOT_MOON_BGM = register("music.frost_moon");
  
  public static final RegistryObject<SoundEvent> CALM_NIGHT_BGM = register("music.calm_night");
  
  public static final RegistryObject<SoundEvent> BLIZZARD_AMBIENT = register("ambient.blizzard");
  
  public static final RegistryObject<SoundEvent> GOKKUR_IDLE = register("entity.gokkur.idle");
  
  public static final RegistryObject<SoundEvent> GOKKUR_HURT = register("entity.gokkur.hurt");
  
  public static final RegistryObject<SoundEvent> GOKKUR_DEATH = register("entity.gokkur.death");
  
  private static RegistryObject<SoundEvent> register(String name) {
    return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation("frostrealm", name)));
  }
}
