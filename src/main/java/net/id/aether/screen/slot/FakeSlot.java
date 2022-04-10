package net.id.aether.screen.slot;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SaddleItem;
import net.minecraft.screen.slot.Slot;

import java.security.Provider;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A slot without a real inventory, uses the provided callbacks instead.
 */
public class FakeSlot extends Slot {
    private final Supplier<ItemStack> getter;
    private final Consumer<ItemStack> setter;
    private final Predicate<ItemStack> filter;
    
    /**
     *
     * @param x The X pos of the slot
     * @param y The Y pos of the slot
     * @param getter Consumes set stacks
     * @param setter Provides stacks to slots
     * @param filter Filters allowed stacks
     */
    public FakeSlot(int x, int y, Supplier<ItemStack> getter, Consumer<ItemStack> setter, Predicate<ItemStack> filter) {
        super(new SimpleInventory(0), 0, x, y);
        this.getter = getter;
        this.setter = setter;
        this.filter = filter;
    }
    
    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isEmpty() || filter.test(stack);
    }
    
    @Override
    public ItemStack getStack() {
        return getter.get();
    }
    
    @Override
    public boolean hasStack() {
        return !getStack().isEmpty();
    }
    
    @Override
    public void setStack(ItemStack stack) {
        if(stack.equals(getStack())){
            return;
        }
        setter.accept(stack);
        markDirty();
    }
    
    @Override
    public ItemStack takeStack(int amount) {
        var existing = getStack();
        var result = existing.split(amount);
        setStack(existing);
        return result;
    }
    
    @Override
    public void markDirty() {}
}
